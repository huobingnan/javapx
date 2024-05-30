package buddha.jvmabi;

import buddha.jvmabi.field.FieldItem;
import buddha.jvmabi.method.MethodItem;
import buddha.jvmabi.reader.DefaultByteCodeReader;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.reader.ReadByteCodeException;
import buddha.jvmabi.attribute.*;
import buddha.jvmabi.constant.*;
import lombok.Getter;

import java.io.InputStream;

@Getter
public final class ClassFile {

    private int magic;                                 // 魔数
    private short minorVersion;                        // 次版本号
    private short majorVersion;                        // 主版本号
    // private short constantPoolCount;                // 常量池大小 => 体现在constantpool中
    private ClassFileConstantPool constantPool;     // 常量池
    private short accessFlags;                         // 本类的访问权限
    private short thisClass;                           // 本类在常量池中的索引
    private short superClass;                          // 超类在常量池中的索引
    private short[] interfaces;                        // 实现的接口集合
    private ClassFileFieldTable fieldTable;         // 字段表
    private ClassFileAttrTable attributeTable;      // 属性表
    private ClassFileMethodTable methodTable;       // 方法表

    public static ClassFile parse(InputStream is) {
        return parse(new DefaultByteCodeReader(is));
    }
    // Parse JVM Class File
    public static ClassFile parse(final IByteCodeReader byteCodeReader)  {
        final ClassFile classFile = new ClassFile();
        try(byteCodeReader) {
            // parse header info
            classFile.magic = byteCodeReader.readU4();
            classFile.minorVersion = byteCodeReader.readU2();
            classFile.majorVersion = byteCodeReader.readU2();
            classFile.constantPool = new ClassFileConstantPool(byteCodeReader.readU2());

            // parse constant pool
            parseConstantPool(classFile.constantPool, byteCodeReader);

            // parse this class descriptors
            classFile.accessFlags = byteCodeReader.readU2();
            classFile.thisClass = byteCodeReader.readU2();
            classFile.superClass = byteCodeReader.readU2();
            classFile.interfaces = new short[byteCodeReader.readU2()];

            // parse interfaces index
            for (int i = 0, len = classFile.interfaces.length; i < len; i++) {
                classFile.interfaces[i] = byteCodeReader.readU2();
            }
            // parse field table
            classFile.fieldTable = new ClassFileFieldTable(byteCodeReader.readU2());
            parseFieldTable(classFile.fieldTable, classFile.constantPool, byteCodeReader);
            // parse method table
            classFile.methodTable = new ClassFileMethodTable(byteCodeReader.readU2());
            parseMethodTable(classFile.methodTable, classFile.constantPool, byteCodeReader);
            // parse attribute table
            classFile.attributeTable = new ClassFileAttrTable(byteCodeReader.readU2());
            parseAttributeTable(classFile.attributeTable, classFile.constantPool, byteCodeReader);
            return classFile;
        }catch (Exception ex) { throw new ReadByteCodeException(ex); }
    }

    private static void parseConstantPool(ClassFileConstantPool pool, IByteCodeReader reader) {
        for (int i = 1, len = pool.length(); i < len; i++) {
            pool.append(parseConstant(reader));
        }
    }

    private static void parseAttributeTable(ClassFileAttrTable table,
                                            ClassFileConstantPool pool,
                                            IByteCodeReader reader) {
        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseAttribute(pool, reader));
        }
    }

    private static void parseFieldTable(ClassFileFieldTable table,
                                        ClassFileConstantPool pool,
                                        IByteCodeReader reader) {
        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseField(pool, reader));
        }
    }

    private static void parseMethodTable(ClassFileMethodTable table,
                                         ClassFileConstantPool pool,
                                         IByteCodeReader reader) {

        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseMethod(pool, reader));
        }
    }

    public static MethodItem parseMethod(ClassFileConstantPool pool, IByteCodeReader reader) {
        final MethodItem item = new MethodItem();
        item.setAccessFlags(reader.readU2());
        item.setNameIndex(reader.readU2());
        item.setDescriptorIndex(reader.readU2());
        final IJvmAttribute[] attributes = new IJvmAttribute[reader.readU2()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = parseAttribute(pool, reader);
        }
        item.setAttributes(attributes);
        return item;
    }

    public static FieldItem parseField(ClassFileConstantPool pool, IByteCodeReader reader) {
        final FieldItem item = new FieldItem();
        item.setAccessFlags(reader.readU2());
        item.setNameIndex(reader.readU2());
        item.setDescriptorIndex(reader.readU2());
        final IJvmAttribute[] attributes = new IJvmAttribute[reader.readU2()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = parseAttribute(pool, reader);
        }
        item.setAttributes(attributes);
        return item;
    }

     public static IJvmAttribute parseAttribute(ClassFileConstantPool pool, IByteCodeReader reader) {
        final short attrNameIndex = reader.readU2(); // attribute name index
        final String name = pool.<ConstantUtf8>getExact(attrNameIndex).contentToString(); // attribute name
        switch (name) {
            case "Code":
                return new CodeAttr(pool, reader);
            case "LineNumberTable":
                return new LineNumberTableAttr(pool, reader);
            case "Exceptions":
                return new ExceptionsAttr(pool, reader);
            case "LocalVariableTable":
                return new LocalVariableTableAttr(pool, reader);
            case "SourceFile":
                return new SourceFileAttr(pool, reader);
            case "ConstantValue":
                return new ConstantValueAttr(pool, reader);
            case "Deprecated":
                return new DeprecatedAttr(); // boolean型属性
            case "Synthetic":
                return new SyntheticAttr(); // boolean型属性
            case "InnerClasses":
                return new InnerClassesAttr(pool, reader);
            case "BootstrapMethods":
                return new BoostrapMethodsAttr(pool, reader);
            case "MethodParameters":
                return new MethodParametersAttr(pool, reader);
            case "StackMapTable":
                // the most complex attribute
                return new StackMapTableAttr(pool, reader);
            default:
                throw new ReadByteCodeException(
                        String.format("Unsupported attribute named %s", name)
                );
        }

    }


    public static IJvmConstant parseConstant(IByteCodeReader reader) {
        // read tag
        final byte tag = reader.readU1();
        switch (tag) {
            case ClassFileConstantTagConst.UTF8_INFO:
                return new ConstantUtf8(reader);
            case ClassFileConstantTagConst.INTEGER_INFO:
                return new ConstantInteger(reader);
            case ClassFileConstantTagConst.FLOAT_INFO:
                return new ConstantFloat(reader);
            case ClassFileConstantTagConst.LONG_INFO:
                return new ConstantLong(reader);
            case ClassFileConstantTagConst.DOUBLE_INFO:
                return new ConstantDouble(reader);
            case ClassFileConstantTagConst.CLASS_INFO:
                return new ConstantClass(reader);
            case ClassFileConstantTagConst.STRING_INFO:
                return new ConstantString(reader);
            case ClassFileConstantTagConst.FIELD_REF_INFO:
                return new ConstantFieldRef(reader);
            case ClassFileConstantTagConst.METHOD_REF_INFO:
                return new ConstantMethodRef(reader);
            case ClassFileConstantTagConst.INTERFACE_METHOD_REF_INFO:
                return new ConstantInterfaceMethodRef(reader);
            case ClassFileConstantTagConst.NAME_AND_TYPE_INFO:
                return new ConstantNameAndType(reader);
            case ClassFileConstantTagConst.METHOD_HANDLE_INFO:
                return new ConstantMethodHandle(reader);
            case ClassFileConstantTagConst.METHOD_TYPE_INFO:
                return new ConstantMethodType(reader);
            case ClassFileConstantTagConst.DYNAMIC_INFO:
                return new ConstantDynamic(reader);
            case ClassFileConstantTagConst.INVOKE_DYNAMIC_INFO:
                return new ConstantInvokeDynamic(reader);
            case ClassFileConstantTagConst.MODULE_INFO:
                return new ConstantModule(reader);
            case ClassFileConstantTagConst.PACKAGE_INFO:
                return new ConstantPackage(reader);
            default:
                throw new ReadByteCodeException(
                        String.format("Unsupported constant with tag '%s'", tag)
                );
        }
    }
}

package ano.nunu.jvmabi;

import ano.nunu.jvmabi.attribute.*;
import ano.nunu.jvmabi.constant.*;
import ano.nunu.jvmabi.attribute.*;
import ano.nunu.jvmabi.constant.*;
import ano.nunu.jvmabi.field.FieldItem;
import ano.nunu.jvmabi.method.MethodItem;
import ano.nunu.jvmabi.reader.BufferedByteCodeReader;
import ano.nunu.jvmabi.reader.IByteCodeReader;
import ano.nunu.jvmabi.reader.ReadByteCodeException;
import lombok.Getter;

import java.io.InputStream;

@Getter
public final class JvmClassFile {

    private int magic;                                 // 魔数
    private short minorVersion;                        // 次版本号
    private short majorVersion;                        // 主版本号
    // private short constantPoolCount;                // 常量池大小 => 体现在constantpool中
    private JvmClassFileConstantPool constantPool;     // 常量池
    private short accessFlags;                         // 本类的访问权限
    private short thisClass;                           // 本类在常量池中的索引
    private short superClass;                          // 超类在常量池中的索引
    private short[] interfaces;                        // 实现的接口集合
    private JvmClassFileFieldTable fieldTable;         // 字段表
    private JvmClassFileAttrTable attributeTable;      // 属性表
    private JvmClassFileMethodTable methodTable;       // 方法表

    // Parse JVM Class File
    public static JvmClassFile parse(InputStream is)  {
        final JvmClassFile classFile = new JvmClassFile();
        try(final IByteCodeReader byteCodeReader = new BufferedByteCodeReader(is)) {
            // parse header info
            classFile.magic = byteCodeReader.readU4();
            classFile.minorVersion = byteCodeReader.readU2();
            classFile.majorVersion = byteCodeReader.readU2();
            classFile.constantPool = new JvmClassFileConstantPool(byteCodeReader.readU2());

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
            classFile.fieldTable = new JvmClassFileFieldTable(byteCodeReader.readU2());
            parseFieldTable(classFile.fieldTable, classFile.constantPool, byteCodeReader);
            // parse method table
            classFile.methodTable = new JvmClassFileMethodTable(byteCodeReader.readU2());
            parseMethodTable(classFile.methodTable, classFile.constantPool, byteCodeReader);
            // parse attribute table
            classFile.attributeTable = new JvmClassFileAttrTable(byteCodeReader.readU2());
            parseAttributeTable(classFile.attributeTable, classFile.constantPool, byteCodeReader);
            return classFile;
        }catch (Exception ex) { throw new ReadByteCodeException(ex); }
    }

    private static void parseConstantPool(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        for (int i = 1, len = pool.length(); i < len; i++) {
            pool.append(parseConstant(reader));
        }
    }

    private static void parseAttributeTable(JvmClassFileAttrTable table,
                                            JvmClassFileConstantPool pool,
                                            IByteCodeReader reader) {
        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseAttribute(pool, reader));
        }
    }

    private static void parseFieldTable(JvmClassFileFieldTable table,
                                        JvmClassFileConstantPool pool,
                                        IByteCodeReader reader) {
        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseField(pool, reader));
        }
    }

    private static void parseMethodTable(JvmClassFileMethodTable table,
                                         JvmClassFileConstantPool pool,
                                         IByteCodeReader reader) {

        for (int i = 0, len = table.length(); i < len; i++) {
            table.append(parseMethod(pool, reader));
        }
    }

    public static MethodItem parseMethod(JvmClassFileConstantPool pool, IByteCodeReader reader) {
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

    public static FieldItem parseField(JvmClassFileConstantPool pool, IByteCodeReader reader) {
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

    public static IJvmAttribute parseAttribute(JvmClassFileConstantPool pool, IByteCodeReader reader) {
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
            case JvmClassFileConstantEnum.UTF8_INFO:
                return new ConstantUtf8(reader);
            case JvmClassFileConstantEnum.INTEGER_INFO:
                return new ConstantInteger(reader);
            case JvmClassFileConstantEnum.FLOAT_INFO:
                return new ConstantFloat(reader);
            case JvmClassFileConstantEnum.LONG_INFO:
                return new ConstantLong(reader);
            case JvmClassFileConstantEnum.DOUBLE_INFO:
                return new ConstantDouble(reader);
            case JvmClassFileConstantEnum.CLASS_INFO:
                return new ConstantClass(reader);
            case JvmClassFileConstantEnum.STRING_INFO:
                return new ConstantString(reader);
            case JvmClassFileConstantEnum.FIELD_REF_INFO:
                return new ConstantFieldRef(reader);
            case JvmClassFileConstantEnum.METHOD_REF_INFO:
                return new ConstantMethodRef(reader);
            case JvmClassFileConstantEnum.INTERFACE_METHOD_REF_INFO:
                return new ConstantInterfaceMethodRef(reader);
            case JvmClassFileConstantEnum.NAME_AND_TYPE_INFO:
                return new ConstantNameAndType(reader);
            case JvmClassFileConstantEnum.METHOD_HANDLE_INFO:
                return new ConstantMethodHandle(reader);
            case JvmClassFileConstantEnum.METHOD_TYPE_INFO:
                return new ConstantMethodType(reader);
            case JvmClassFileConstantEnum.DYNAMIC_INFO:
                return new ConstantDynamic(reader);
            case JvmClassFileConstantEnum.INVOKE_DYNAMIC_INFO:
                return new ConstantInvokeDynamic(reader);
            case JvmClassFileConstantEnum.MODULE_INFO:
                return new ConstantModule(reader);
            case JvmClassFileConstantEnum.PACKAGE_INFO:
                return new ConstantPackage(reader);
            default:
                throw new ReadByteCodeException(
                        String.format("Unsupported constant with tag '%s'", tag)
                );
        }
    }
}

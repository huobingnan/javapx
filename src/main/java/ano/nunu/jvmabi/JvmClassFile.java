package ano.nunu.jvmabi;

import ano.nunu.jvmabi.attribute.*;
import ano.nunu.jvmabi.constant.*;
import ano.nunu.jvmabi.attribute.*;
import ano.nunu.jvmabi.constant.*;
import ano.nunu.jvmabi.reader.BufferedByteCodeReader;
import ano.nunu.jvmabi.reader.IByteCodeReader;
import ano.nunu.jvmabi.reader.ReadByteCodeException;

import java.io.InputStream;

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
    private JvmClassFileAttrTable attributes;          // 属性表

    public int getMagic() { return magic; }

    public short getMinorVersion() { return minorVersion; }

    public short getMajorVersion() { return majorVersion; }

    public JvmClassFileConstantPool getConstantPool() { return constantPool; }

    public short getAccessFlags() { return accessFlags; }

    public short getThisClass() { return thisClass; }

    public short getSuperClass() { return superClass; }

    public short[] getInterfaces() { return interfaces; }

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
            case "StackMapFrame":
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

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
            parseConstant(pool, reader);
        }
    }

    private static void parseAttributeTable(JvmClassFileAttrTable table,
                                            JvmClassFileConstantPool pool,
                                            IByteCodeReader reader) {
        for (int i = 0, len = table.length(); i < len; i++) {
            parseAttribute(table, pool, reader);
        }
    }

    private static void parseAttribute(JvmClassFileAttrTable table,
                                       JvmClassFileConstantPool pool,
                                       IByteCodeReader reader) {
        final short attrNameIndex = reader.readU2(); // attribute name index
        final String name = pool.<ConstantUtf8>getExact(attrNameIndex).contentToString(); // attribute name
        switch (name) {
            case "Code": break;
            case "LineNumberTable":
                table.append(new LineNumberTableAttr(pool, reader));
                break;
            case "Exceptions":
                table.append(new ExceptionsAttr(pool, reader));
                break;
            case "LocalVariableTable":
                table.append(new LocalVariableTableAttr(pool, reader));
                break;
            case "SourceFile":
                table.append(new SourceFileAttr(pool, reader));
                break;
            case "ConstantValue":
                table.append(new ConstantValueAttr(pool, reader));
                break;
            case "Deprecated":
                table.append(new DeprecatedAttr()); // boolean型属性
                break;
            case "Synthetic":
                table.append(new SyntheticAttr()); // boolean型属性
                break;
            case "InnerClasses":
                table.append(new InnerClassesAttr(pool, reader));
                break;
            default:
                throw new ReadByteCodeException(
                        String.format("Unsupported attribute named %s", name)
                );
        }

    }


    private static void parseConstant(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        // read tag
        final byte tag = reader.readU1();
        switch (tag) {
            case JvmClassFileConstantEnum.UTF8_INFO:
                pool.append(new ConstantUtf8(reader));
                break;
            case JvmClassFileConstantEnum.INTEGER_INFO:
                pool.append(new ConstantInteger(reader));
                break;
            case JvmClassFileConstantEnum.FLOAT_INFO:
                pool.append(new ConstantFloat(reader));
                break;
            case JvmClassFileConstantEnum.LONG_INFO:
                pool.append(new ConstantLong(reader));
                break;
            case JvmClassFileConstantEnum.DOUBLE_INFO:
                pool.append(new ConstantDouble(reader));
                break;
            case JvmClassFileConstantEnum.CLASS_INFO:
                pool.append(new ConstantClass(reader));
                break;
            case JvmClassFileConstantEnum.STRING_INFO:
                pool.append(new ConstantString(reader));
                break;
            case JvmClassFileConstantEnum.FIELD_REF_INFO:
                pool.append(new ConstantFieldRef(reader));
                break;
            case JvmClassFileConstantEnum.METHOD_REF_INFO:
                pool.append(new ConstantMethodRef(reader));
                break;
            case JvmClassFileConstantEnum.INTERFACE_METHOD_REF_INFO:
                pool.append(new ConstantInterfaceMethodRef(reader));
                break;
            case JvmClassFileConstantEnum.NAME_AND_TYPE_INFO:
                pool.append(new ConstantNameAndType(reader));
                break;
            case JvmClassFileConstantEnum.METHOD_HANDLE_INFO:
                pool.append(new ConstantMethodHandle(reader));
                break;
            case JvmClassFileConstantEnum.METHOD_TYPE_INFO:
                pool.append(new ConstantMethodType(reader));
                break;
            case JvmClassFileConstantEnum.DYNAMIC_INFO:
                pool.append(new ConstantDynamic(reader));
                break;
            case JvmClassFileConstantEnum.INVOKE_DYNAMIC_INFO:
                pool.append(new ConstantInvokeDynamic(reader));
                break;
            case JvmClassFileConstantEnum.MODULE_INFO:
                pool.append(new ConstantModule(reader));
                break;
            case JvmClassFileConstantEnum.PACKAGE_INFO:
                pool.append(new ConstantPackage(reader));
                break;
            default:
                throw new ReadByteCodeException(
                        String.format("Unsupported constant with tag '%s'", tag)
                );
        }
    }
}

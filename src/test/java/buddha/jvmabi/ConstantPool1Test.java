package buddha.jvmabi;
import buddha.jvmabi.attribute.BootstrapMethodsAttr;
import buddha.jvmabi.constant.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 常量池解析测试
 * 这个测试类对以下常量类型做了简单测试：
 *  1. CONSTANT_Utf8_info
 *  2. CONSTANT_Methodref_info
 *  3. CONSTANT_Fieldref_info
 *  4. CONSTANT_Integer_info
 *  5. CONSTANT_Float_info
 *  6. CONSTANT_Long_info
 *  7. CONSTANT_Double_info
 *  8. CONSTANT_NameAndType_info
 *  9. CONSTANT_MethodType_info
 *  10.CONSTANT_MethodHandle_info
 *  11.CONSTANT_InvokeDynamic_info
 *  12.CONSTANT_Class_info
 * 未验证的常量
 *  1. CONSTANT_String_info
 *  2. CONSTANT_InterfaceMethodref_info
 */
public class ConstantPool1Test extends BaseTest {

    private final ClassFileConstantPool cp = classFile.getConstantPool();

    @Test
    public void shouldGetCorrectClassInfo() {
        int index = cp.<ConstantClass>getExact(12).getNameIndex();
        assertEquals(14, index);
        assertEquals("java/lang/System", cp.<ConstantUtf8>getExact(index).contentToString());
    }
    @Test
    public void shouldGetCorrectBasicInfo() {
        assertEquals(0, classFile.getMinorVersion());
        assertEquals(65, classFile.getMajorVersion());
        assertEquals(85, classFile.getConstantPool().length());
        assertEquals(23, classFile.getThisClass());
        assertEquals(2, classFile.getSuperClass());
    }

    @Test
    public void shouldGetCorrectUtf8Info() {
        assertEquals("java/lang/Object", cp.<ConstantUtf8>getExact(4).contentToString());
        assertEquals("<init>", cp.<ConstantUtf8>getExact(5).contentToString());
        assertEquals("()V", cp.<ConstantUtf8>getExact(6).contentToString());
        assertEquals("run", cp.<ConstantUtf8>getExact(9).contentToString());
        assertEquals("()Ljava/lang/Runnable;", cp.<ConstantUtf8>getExact(10).contentToString());
    }

    @Test
    public void shouldGetCorrectIntAndFloatInfo() {
        assertEquals(1, cp.<ConstantInteger>getExact(28).getValue());
        assertEquals(1.2, cp.<ConstantFloat>getExact(35).getValue(), 1e-6);
    }

    @Test
    public void shouldGetCorrectRefInfo() {
        // CONSTANT_Method_ref
        assertEquals(2, cp.<ConstantMethodRef>getExact(1).getClassIndex());
        assertEquals(3, cp.<ConstantMethodRef>getExact(1).getNameAndTypeIndex());
        // CONSTANT_Field_ref
        assertEquals(12, cp.<ConstantFieldRef>getExact(11).getClassIndex());
        assertEquals(13, cp.<ConstantFieldRef>getExact(11).getNameAndTypeIndex());
    }

    @Test
    public void shouldCorrectNameAndTypeInfo() {
        assertEquals(5, cp.<ConstantNameAndType>getExact(3).getNameIndex());
        assertEquals(6, cp.<ConstantNameAndType>getExact(3).getDescriptorIndex());
        assertEquals(9, cp.<ConstantNameAndType>getExact(8).getNameIndex());
        assertEquals(10, cp.<ConstantNameAndType>getExact(8).getDescriptorIndex());
        assertEquals(15, cp.<ConstantNameAndType>getExact(13).getNameIndex());
        assertEquals(16, cp.<ConstantNameAndType>getExact(13).getDescriptorIndex());
        assertEquals(21, cp.<ConstantNameAndType>getExact(19).getNameIndex());
        assertEquals(22, cp.<ConstantNameAndType>getExact(19).getDescriptorIndex());
        // 校验内容的正确性
        int index = cp.<ConstantNameAndType>getExact(19).getNameIndex();
        assertEquals("println", cp.<ConstantUtf8>getExact(index).contentToString());
        index = cp.<ConstantNameAndType>getExact(19).getDescriptorIndex();
        assertEquals("(I)V", cp.<ConstantUtf8>getExact(index).contentToString());
        index = cp.<ConstantNameAndType>getExact(71).getNameIndex();
        assertEquals("lambda$bar$0", cp.<ConstantUtf8>getExact(index).contentToString());
        index = cp.<ConstantNameAndType>getExact(71).getDescriptorIndex();
        assertEquals("()V", cp.<ConstantUtf8>getExact(index).contentToString());
        index = cp.<ConstantNameAndType>getExact(75).getNameIndex();
        assertEquals("metafactory", cp.<ConstantUtf8>getExact(index).contentToString());
        index = cp.<ConstantNameAndType>getExact(75).getDescriptorIndex();
        assertEquals(
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                cp.<ConstantUtf8>getExact(index).contentToString()
        );

    }

    @Test
    public void shouldGetCorrectLongAndDoubleInfo() {
        final ClassFileConstantPool cp = classFile.getConstantPool();
        assertEquals(2, cp.<ConstantLong>getExact(31).getValue());
        assertEquals(1.3, cp.<ConstantDouble>getExact(38).getValue(), 1e-6);
        assertNull(cp.get(32));
        assertNull(cp.get(39));
    }

    @Test
    public void shouldGetCorrectMethodHandleInfo() {
        assertEquals(RefKindConst.INVOKE_STATIC, cp.<ConstantMethodHandle>getExact(69).getReferenceKind());
        assertEquals(70, cp.<ConstantMethodHandle>getExact(69).getReferenceIndex());
        int index = cp.<ConstantMethodHandle>getExact(69).getReferenceIndex();
        assertInstanceOf(ConstantMethodRef.class, cp.get(index));
    }

    @Test
    public void shouldGetCorrectMethodTypeInfo() {
        assertEquals(6, cp.<ConstantMethodType>getExact(68).getDescriptorIndex());
        int index = cp.<ConstantMethodType>getExact(68).getDescriptorIndex();
        assertInstanceOf(ConstantUtf8.class, cp.get(index));
    }

    @Test
    public void shouldGetCorrectInvokeDynamicInfo() {
        int index = cp.<ConstantInvokeDynamic>getExact(7).getNameAndTypeIndex();
        assertEquals(8, index);
        assertInstanceOf(ConstantNameAndType.class, cp.get(index));
        index = cp.<ConstantInvokeDynamic>getExact(7).getBootstrapMethodAttributeIndex();
        assertEquals(0, index);
    }


    @Override
    String getClassFilename() {
        return "data/ConstantPoolTest.class";
    }
}

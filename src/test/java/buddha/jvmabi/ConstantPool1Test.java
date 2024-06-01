package buddha.jvmabi;
import buddha.jvmabi.constant.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 常量池解析测试
 */
public class ConstantPool1Test extends BaseTest {


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
        final ClassFileConstantPool cp = classFile.getConstantPool();
        assertEquals("java/lang/Object", cp.<ConstantUtf8>getExact(4).contentToString());
        assertEquals("<init>", cp.<ConstantUtf8>getExact(5).contentToString());
        assertEquals("()V", cp.<ConstantUtf8>getExact(6).contentToString());
        assertEquals("run", cp.<ConstantUtf8>getExact(9).contentToString());
        assertEquals("()Ljava/lang/Runnable;", cp.<ConstantUtf8>getExact(10).contentToString());
    }

    @Test
    public void shouldGetCorrectIntAndFloatInfo() {
        final ClassFileConstantPool cp = classFile.getConstantPool();
        assertEquals(1, cp.<ConstantInteger>getExact(28).getValue());
        assertEquals(1.2, cp.<ConstantFloat>getExact(35).getValue(), 1e-6);
    }

    @Test
    public void shouldGetCorrectLongAndDoubleInfo() {
        final ClassFileConstantPool cp = classFile.getConstantPool();
        assertEquals(2, cp.<ConstantLong>getExact(31).getValue());
        assertEquals(1.3, cp.<ConstantDouble>getExact(38).getValue(), 1e-6);
        assertNull(cp.get(32));
        assertNull(cp.get(39));
    }

    @Override
    String getClassFilename() {
        return "data/ConstantPoolTest.class";
    }
}

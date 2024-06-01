package buddha.jvmabi;
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

    @Override
    String getClassFilename() {
        return "data/ConstantPoolTest.class";
    }
}

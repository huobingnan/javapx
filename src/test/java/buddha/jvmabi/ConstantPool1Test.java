package buddha.jvmabi;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 常量池解析测试
 */
public class ConstantPool1Test extends BaseTest {

    /**
     * 常量池的大小测试
     */
    @Test
    public void poolSizeTest() {
        final ClassFileConstantPool constantPool = classFile.getConstantPool();
        assertEquals(25, constantPool.length());
    }

    @Override
    String getClassFilename() {
        return "data/BiLock.class";
    }
}

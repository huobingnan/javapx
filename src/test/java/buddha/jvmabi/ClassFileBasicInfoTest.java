package buddha.jvmabi;

import buddha.jvmabi.constant.ConstantNameAndType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ClassFile基本信息测试类
 * @author BRYAN
 */
public class ClassFileBasicInfoTest extends BaseTest {

    @Test
    public void shouldGetCorrectHeaderInfo() throws Throwable {
        assertEquals(0xCAFEBABE, classFile.getMagic());
        assertEquals(0x0000, classFile.getMinorVersion());
        assertEquals(0x003D, classFile.getMajorVersion());
        assertEquals(0x0019, classFile.getConstantPool().length());
    }

    @Test
    public void shouldGetCorrectConstantPoolInfo() throws Throwable {
        final ClassFileConstantPool pool = classFile.getConstantPool();
        assertEquals(25, pool.length());
        assertEquals(ClassFileConstantTagConst.METHOD_REF_INFO, pool.get(1).getTag());
        assertEquals(ClassFileConstantTagConst.CLASS_INFO, pool.get(2).getTag());
        assertEquals(ClassFileConstantTagConst.NAME_AND_TYPE_INFO, pool.get(3).getTag());
        assertEquals(ClassFileConstantTagConst.UTF8_INFO, pool.get(6).getTag());
        assertEquals(ClassFileConstantTagConst.UTF8_INFO, pool.get(24).getTag());
        final int nameIndex = pool.<ConstantNameAndType>getExact(9).getNameIndex();
        final int descIndex = pool.<ConstantNameAndType>getExact(9).getDescriptorIndex();
        assertEquals(11, nameIndex);
        assertEquals(12, descIndex);
        for (int i = 1, len = pool.length(); i < len; i++) {
            System.out.println(i + ": " + pool.get(i));
        }
    }

    @Test
    public void shouldGetCorrectDescriptorInfo() throws Throwable {
        assertEquals(8, classFile.getThisClass());
        assertEquals(2, classFile.getSuperClass());
        assertEquals(0, classFile.getInterfaces().length);
    }


    @Override
    String getClassFilename() {
        return "data/BiLock.class";
    }
}

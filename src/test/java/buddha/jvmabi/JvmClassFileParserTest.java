package buddha.jvmabi;

import buddha.jvmabi.constant.ConstantNameAndType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

public class JvmClassFileParserTest {

    @Test
    public void shouldGetCorrectHeaderInfo() throws Throwable {
        final JvmClassFile classFile = JvmClassFile.parse(new FileInputStream("data/BiLock.class"));
        assertEquals(0xCAFEBABE, classFile.getMagic());
        assertEquals(0x0000, classFile.getMinorVersion());
        assertEquals(0x003D, classFile.getMajorVersion());
        assertEquals(0x0019, classFile.getConstantPool().length());
    }

    @Test
    public void shouldGetCorrectConstantPoolInfo() throws Throwable {
        final JvmClassFile classFile = JvmClassFile.parse(new FileInputStream("data/BiLock.class"));
        final JvmClassFileConstantPool pool = classFile.getConstantPool();
        assertEquals(25, pool.length());
        assertEquals(JvmClassFileConstantEnum.METHOD_REF_INFO, pool.get(1).getTag());
        assertEquals(JvmClassFileConstantEnum.CLASS_INFO, pool.get(2).getTag());
        assertEquals(JvmClassFileConstantEnum.NAME_AND_TYPE_INFO, pool.get(3).getTag());
        assertEquals(JvmClassFileConstantEnum.UTF8_INFO, pool.get(6).getTag());
        assertEquals(JvmClassFileConstantEnum.UTF8_INFO, pool.get(24).getTag());
        final short nameIndex = pool.<ConstantNameAndType>getExact(9).getNameIndex();
        final short descIndex = pool.<ConstantNameAndType>getExact(9).getDescriptorIndex();
        assertEquals(11, nameIndex);
        assertEquals(12, descIndex);
        for (int i = 1, len = pool.length(); i < len; i++) {
            System.out.println(i + ": " + pool.get(i));
        }
    }

    @Test
    public void shouldGetCorrectDescriptorInfo() throws Throwable {
        final JvmClassFile classFile = JvmClassFile.parse(new FileInputStream("data/BiLock.class"));
        assertEquals(8, classFile.getThisClass());
        assertEquals(2, classFile.getSuperClass());
        assertEquals(0, classFile.getInterfaces().length);
    }

    @Test
    public void shouldGetCorrectAttribute() throws Throwable {

    }
}

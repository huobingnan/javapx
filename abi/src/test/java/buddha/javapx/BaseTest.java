package buddha.javapx;

import buddha.javapx.abi.ClassFile;
import buddha.javapx.abi.reader.DefaultByteCodeReader;
import buddha.javapx.abi.reader.IByteCodeReader;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class BaseTest {

    protected final IByteCodeReader reader;
    protected final ClassFile classFile;

    public BaseTest() {
        try {
            System.out.println(System.getProperty("user.dir"));
            reader = new DefaultByteCodeReader(new FileInputStream(getClassFilename()));
            classFile = ClassFile.parse(reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    abstract String getClassFilename();
}

package buddha.jvmabi;

import buddha.jvmabi.reader.DefaultByteCodeReader;
import buddha.jvmabi.reader.IByteCodeReader;

import java.io.FileInputStream;
import java.io.IOException;

public abstract class BaseTest {

    protected final IByteCodeReader reader;
    protected final ClassFile classFile;

    public BaseTest() {
        try {
            reader = new DefaultByteCodeReader(new FileInputStream(getClassFilename()));
            classFile = ClassFile.parse(reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    abstract String getClassFilename();
}

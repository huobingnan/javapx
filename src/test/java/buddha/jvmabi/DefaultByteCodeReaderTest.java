package buddha.jvmabi;

import buddha.jvmabi.reader.DefaultByteCodeReader;
import buddha.jvmabi.reader.IByteCodeReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultByteCodeReaderTest {

    private final IByteCodeReader reader = new DefaultByteCodeReader(new FileInputStream("data/BiLock.class"));
    public DefaultByteCodeReaderTest() throws Throwable { }

    @Test
    public void shouldGetMagicNumber() {
        final int magic = reader.readU4();
        assertEquals("CAFEBABE", String.format("%X", magic));
    }

}

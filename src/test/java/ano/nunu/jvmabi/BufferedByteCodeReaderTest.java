package ano.nunu.jvmabi;

import ano.nunu.jvmabi.reader.BufferedByteCodeReader;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class BufferedByteCodeReaderTest {
    final BufferedByteCodeReader reader = new BufferedByteCodeReader(new FileInputStream("data/BiLock.class"));

    public BufferedByteCodeReaderTest() throws Throwable { }

    @Test
    public void shouldGetMagicNumber() {
        final int magic = reader.readU4();
        assertEquals("CAFEBABE", String.format("%X", magic));
    }

}

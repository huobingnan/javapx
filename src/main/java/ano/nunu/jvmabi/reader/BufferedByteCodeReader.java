package ano.nunu.jvmabi.reader;

import ano.nunu.jvmabi.Unsigned;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * BufferedByteCodeReader is not a concurrency safe class especially using <strong>readUN</strong> method.
 */
public class BufferedByteCodeReader implements IByteCodeReader {

    private final BufferedInputStream bis;
    private final byte[] buffer;
    private int offset;

    public BufferedByteCodeReader(InputStream is) {
        bis = new BufferedInputStream(is);
        offset = 0;
        buffer = new byte[8];
    }

    @Override
    public byte readU1() {
        try {
            final byte u1 = (byte)bis.read();
            offset += 1;
            return u1;
        }catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public short readU2() {
        try {
            int u2 = 0;
            bis.read(buffer, 0, 2);
            u2 = u2 | (Unsigned.cbi(buffer[0]) << 8);
            u2 = u2 | (Unsigned.cbi(buffer[1]));
            offset += 2;
            return (short) u2;
        }catch (IOException ex) { throw new ReadByteCodeException(ex); }
    }

    @Override
    public int readU4() {
        try {
            int u4 = 0;
            bis.read(buffer, 0, 4);
            for (int i = 0; i <= 2; i++) {
                u4 = u4 | (Unsigned.cbi(buffer[i]) << ((3 - i) * 8));
            }
            u4 = u4 | Unsigned.cbi(buffer[3]);
            offset += 4;
            return u4;
        }catch (IOException ex) { throw new ReadByteCodeException(ex); }
    }

    @Override
    public long readU8() {
        try {
            long u8 = 0;
            bis.read(buffer);
            for (int i = 0; i <= 6; i++) {
                u8 = u8 | (Unsigned.cbi(buffer[i]) << ((7 - i) * 8));
            }
            u8 = u8 | (Unsigned.cbi(buffer[7]));
            offset += 8;
            return u8;
        }catch (IOException ex) { throw new ReadByteCodeException(ex); }
    }

    @Override
    public void close() throws Exception { bis.close(); }

    public int getOffset() { return offset; }
}

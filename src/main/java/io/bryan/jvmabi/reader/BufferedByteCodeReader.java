package io.bryan.jvmabi.reader;

import io.bryan.jvmabi.Unsigned;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BufferedByteCodeReader implements IByteCodeReader, Closeable {

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
            return (byte)bis.read();
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
            return u8;
        }catch (IOException ex) { throw new ReadByteCodeException(ex); }
    }

    @Override
    public void close() throws IOException { bis.close(); }
}

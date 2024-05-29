package buddha.jvmabi.reader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 默认的字节码读取器
 * author: BRYAN
 */
public class DefaultByteCodeReader implements IByteCodeReader {
    private final DataInputStream dis;

    public DefaultByteCodeReader(InputStream is) {
        dis = new DataInputStream(is);
    }
    @Override
    public byte readU1() {
        try {
            return dis.readByte();
        } catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public short readU2() {
        try {
            return dis.readShort();
        } catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public int readU4() {
        try {
            return dis.readInt();
        } catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public long readU8() {
        try {
            return dis.readLong();
        } catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public void close() throws Exception { dis.close(); }
}

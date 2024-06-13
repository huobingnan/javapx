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
    public int readU1() {
        try {
            return dis.readUnsignedByte();
        } catch (IOException ex) {
            throw new ReadByteCodeException(ex);
        }
    }

    @Override
    public int readU2() {
        try {
            return dis.readUnsignedShort();
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

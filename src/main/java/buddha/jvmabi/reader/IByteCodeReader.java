package buddha.jvmabi.reader;

/**
 * 字节码读取接口
 * @author BRYAN
 */
public interface IByteCodeReader extends AutoCloseable {
    byte  readU1();
    short readU2();
    int   readU4();
    long  readU8();
}

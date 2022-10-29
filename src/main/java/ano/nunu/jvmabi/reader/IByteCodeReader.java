package ano.nunu.jvmabi.reader;

public interface IByteCodeReader extends AutoCloseable {
    byte  readU1();
    short readU2();
    int   readU4();
    long  readU8();
}

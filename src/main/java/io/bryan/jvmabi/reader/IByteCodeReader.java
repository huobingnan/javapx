package io.bryan.jvmabi.reader;

public interface IByteCodeReader {
    byte  readU1();
    short readU2();
    int   readU4();
    long  readU8();
}

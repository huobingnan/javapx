package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.reader.IByteCodeReader;

public interface IJvmConstant {
    byte getTag();

    void read(IByteCodeReader reader);
}

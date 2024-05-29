package buddha.jvmabi.constant;

import buddha.jvmabi.reader.IByteCodeReader;

public interface IJvmConstant {
    byte getTag();

    void read(IByteCodeReader reader);
}

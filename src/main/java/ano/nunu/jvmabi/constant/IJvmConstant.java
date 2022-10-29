package ano.nunu.jvmabi.constant;

import ano.nunu.jvmabi.reader.IByteCodeReader;

public interface IJvmConstant {
    byte getTag();

    void read(IByteCodeReader reader);
}

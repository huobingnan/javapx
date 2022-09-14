package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantUtf8 implements Serializable, IJvmConstant {
    private byte[] bytes;

    public ConstantUtf8() {}

    public ConstantUtf8(byte[] bytes) { this.bytes = bytes; }

    public ConstantUtf8(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.UTF8_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bytes = new byte[reader.readU2()];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = reader.readU1();
    }
}

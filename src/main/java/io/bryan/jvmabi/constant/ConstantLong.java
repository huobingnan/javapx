package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantLong implements Serializable, IJvmConstant {

    private long value;

    public ConstantLong() {}

    public ConstantLong(long longValue) { value = longValue; }

    public ConstantLong(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.LONG_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = reader.readU8(); }

    @Override
    public String toString() {
        return "<CONSTANT_Long_Info, " + value + ">";
    }
}

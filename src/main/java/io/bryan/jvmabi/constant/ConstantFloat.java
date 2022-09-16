package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantFloat implements Serializable, IJvmConstant {
    private float value;

    public ConstantFloat() {}

    public ConstantFloat(float value) { this.value = value; }

    public ConstantFloat(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.FLOAT_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = Float.intBitsToFloat(reader.readU4()); }

    public float getFloatValue() { return value; }

    @Override
    public String toString() {
        return "<CONSTANT_Float_Info, " + value + ">";
    }
}

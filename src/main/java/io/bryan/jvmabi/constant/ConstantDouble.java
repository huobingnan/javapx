package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantDouble implements Serializable, IJvmConstant {

    private double value;

    public ConstantDouble() {}

    public ConstantDouble(double value) { this.value = value; }

    public ConstantDouble(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.DOUBLE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = Double.longBitsToDouble(reader.readU4()); }

    public double getDoubleValue() { return value; }

    @Override
    public String toString() {
       return "<CONSTANT_Double_Info, " + value + ">";
    }
}

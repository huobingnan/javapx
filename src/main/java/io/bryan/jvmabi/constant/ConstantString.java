package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantString implements Serializable, IJvmConstant {

    private short index;

    public ConstantString() {}

    public ConstantString(short index) { this.index = index; }

    public ConstantString(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.STRING_INFO; }

    @Override
    public void read(IByteCodeReader reader) { index = reader.readU2(); }

    public short getUtf8Index() { return index; }

    @Override
    public String toString() {
        return "<CONSTANT_String_Info, #" + index + ">";
    }
}

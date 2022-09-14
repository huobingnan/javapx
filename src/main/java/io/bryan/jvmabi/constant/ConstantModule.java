package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantModule implements Serializable, IJvmConstant {

    private short nameIndex; // 指向常量池中一个CONSTANT_Utf8_Info

    public ConstantModule() {}

    public ConstantModule(short nameIndex) { this.nameIndex = nameIndex; }

    public ConstantModule(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.MODULE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { nameIndex = reader.readU2(); }

    public short getNameIndex() { return nameIndex; }
}

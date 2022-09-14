package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantDynamic implements Serializable, IJvmConstant {

    private short bootstrapMethodAttributeIndex;
    private short nameAndTypeIndex;

    public ConstantDynamic() {}

    public ConstantDynamic(short bootstrapMethodAttributeIndex, short nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public ConstantDynamic(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.DYNAMIC_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bootstrapMethodAttributeIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    public short getBootstrapMethodAttributeIndex() { return bootstrapMethodAttributeIndex; }

    public short getNameAndTypeIndex() { return nameAndTypeIndex; }
}

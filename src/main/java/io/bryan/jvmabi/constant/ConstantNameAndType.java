package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantNameAndType implements Serializable, IJvmConstant {

    private short nameIndex;       // 指向常量池中一个名称常量
    private short descriptorIndex; // 指向常量池中一个描述符常量

    public ConstantNameAndType() {}

    public ConstantNameAndType(short nameIndex, short descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public ConstantNameAndType(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.NAME_AND_TYPE_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }

    public short getNameIndex() { return nameIndex; }

    public short getDescriptorIndex() { return descriptorIndex; }

    @Override
    public String toString() {
        return "<CONSTANT_NameAndType_Info, #" + nameIndex + " #" + descriptorIndex + ">";
    }
}

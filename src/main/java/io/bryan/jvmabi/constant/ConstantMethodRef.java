package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantMethodRef implements Serializable, IJvmConstant {

    private short classIndex;       // 指向常量池中的一个CONSTANT_Class_Info
    private short nameAndTypeIndex; // 指向常量池中的一个CONSTANT_NameAndType_Info

    public ConstantMethodRef() {}

    public ConstantMethodRef(short classIndex, short nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public ConstantMethodRef(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.METHOD_REF_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    public short getClassIndex() { return classIndex; }

    public short getNameAndTypeIndex() { return nameAndTypeIndex; }
}

package io.bryan.jvmabi.constant;

import io.bryan.jvmabi.JvmClassFileConstantEnum;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantMethodHandle implements Serializable, IJvmConstant {

    private short referenceKind;
    private short referenceIndex;

    public ConstantMethodHandle() {}

    public ConstantMethodHandle(short referenceKind, short referenceIndex) {
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    public ConstantMethodHandle(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.METHOD_HANDLE_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        referenceKind = reader.readU2();
        referenceIndex = reader.readU2();
    }

    public short getReferenceKind() { return referenceKind; }

    public short getReferenceIndex() { return referenceIndex; }
}

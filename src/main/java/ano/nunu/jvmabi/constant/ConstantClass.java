package ano.nunu.jvmabi.constant;

import ano.nunu.jvmabi.JvmClassFileConstantEnum;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantClass implements Serializable, IJvmConstant {
    public short nameIndex;

    public ConstantClass() {}

    public ConstantClass(short nameIndex) { this.nameIndex = nameIndex; }

    public ConstantClass(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.CLASS_INFO; }

    @Override
    public void read(IByteCodeReader reader) { nameIndex = reader.readU2(); }

    public short getNameIndex() { return nameIndex; }

    @Override
    public String toString() {
        return "<CONSTANT_Class_Info, #" + nameIndex + ">";
    }
}

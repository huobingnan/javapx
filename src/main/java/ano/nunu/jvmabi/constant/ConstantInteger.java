package ano.nunu.jvmabi.constant;

import ano.nunu.jvmabi.JvmClassFileConstantEnum;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantInteger implements Serializable, IJvmConstant {
    private int value;

    public ConstantInteger() {}

    public ConstantInteger(IByteCodeReader reader) { read(reader); }
    @Override
    public byte getTag() { return JvmClassFileConstantEnum.INTEGER_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = reader.readU4(); }

    public int getIntValue() { return value; }

    @Override
    public String toString() {
        return "<CONSTANT_Integer_Info, " + value + ">";
    }
}

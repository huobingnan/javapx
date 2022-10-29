package ano.nunu.jvmabi.constant;

import ano.nunu.jvmabi.JvmClassFileConstantEnum;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantMethodType implements Serializable, IJvmConstant {

    private short descriptorIndex;

    public ConstantMethodType() {}

    public ConstantMethodType(short descriptorIndex) { this.descriptorIndex = descriptorIndex; }

    public ConstantMethodType(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.METHOD_TYPE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { descriptorIndex = reader.readU2(); }

    public short getDescriptorIndex() { return descriptorIndex; }

    @Override
    public String toString() {
        return "<CONSTANT_Methodtype_Info, #" + descriptorIndex + ">";

    }
}

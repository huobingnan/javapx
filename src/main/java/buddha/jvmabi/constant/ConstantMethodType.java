package buddha.jvmabi.constant;

import buddha.jvmabi.JvmClassFileConstantEnum;
import buddha.jvmabi.reader.IByteCodeReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ConstantMethodType implements Serializable, IJvmConstant {
    private short descriptorIndex;

    public ConstantMethodType(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.METHOD_TYPE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { descriptorIndex = reader.readU2(); }

    @Override
    public String toString() {
        return "<CONSTANT_Methodtype_Info, #" + descriptorIndex + ">";

    }
}

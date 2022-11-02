package ano.nunu.jvmabi.constant;

import ano.nunu.jvmabi.JvmClassFileConstantEnum;
import ano.nunu.jvmabi.reader.IByteCodeReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ConstantFloat implements Serializable, IJvmConstant {
    private float value;

    public ConstantFloat(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.FLOAT_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = Float.intBitsToFloat(reader.readU4()); }

    @Override
    public String toString() {
        return "<CONSTANT_Float_Info, " + value + ">";
    }
}

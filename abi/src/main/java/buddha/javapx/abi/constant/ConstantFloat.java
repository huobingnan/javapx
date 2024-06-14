package buddha.javapx.abi.constant;

import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import buddha.javapx.abi.reader.IByteCodeReader;
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

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.FLOAT_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = Float.intBitsToFloat(reader.readU4()); }

    @Override
    public String toString() {
        return "<CONSTANT_Float_Info, " + value + ">";
    }
}

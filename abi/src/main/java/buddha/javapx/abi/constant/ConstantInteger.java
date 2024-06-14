package buddha.javapx.abi.constant;

import buddha.javapx.abi.reader.IByteCodeReader;
import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ConstantInteger implements Serializable, IJvmConstant {
    private int value;

    public ConstantInteger(IByteCodeReader reader) { read(reader); }
    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.INTEGER_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = reader.readU4(); }

    @Override
    public String toString() {
        return "<CONSTANT_Integer_Info, " + value + ">";
    }
}

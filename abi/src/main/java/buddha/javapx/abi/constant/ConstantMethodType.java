package buddha.javapx.abi.constant;

import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import buddha.javapx.abi.annotation.U2;
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
public final class ConstantMethodType implements Serializable, IJvmConstant {
    private @U2 int descriptorIndex;

    public ConstantMethodType(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.METHOD_TYPE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { descriptorIndex = reader.readU2(); }

    @Override
    public String toString() {
        return "<CONSTANT_Methodtype_Info, #" + descriptorIndex + ">";

    }
}

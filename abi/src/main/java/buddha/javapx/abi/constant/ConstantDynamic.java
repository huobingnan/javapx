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
public final class ConstantDynamic implements Serializable, IJvmConstant {

    private @U2 int bootstrapMethodAttributeIndex;
    private @U2 int nameAndTypeIndex;

    public ConstantDynamic(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.DYNAMIC_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bootstrapMethodAttributeIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }
    @Override
    public String toString() {
        return "<CONSTANT_Dynamic_Info, @"+ bootstrapMethodAttributeIndex + " #" + nameAndTypeIndex + ">";
    }
}

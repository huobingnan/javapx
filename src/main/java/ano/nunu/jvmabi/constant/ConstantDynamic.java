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
public final class ConstantDynamic implements Serializable, IJvmConstant {

    private short bootstrapMethodAttributeIndex;
    private short nameAndTypeIndex;

    public ConstantDynamic(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.DYNAMIC_INFO; }

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

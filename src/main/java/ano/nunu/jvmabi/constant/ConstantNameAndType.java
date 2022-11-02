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
public final class ConstantNameAndType implements Serializable, IJvmConstant {
    private short nameIndex;       // 指向常量池中一个名称常量
    private short descriptorIndex; // 指向常量池中一个描述符常量

    public ConstantNameAndType(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.NAME_AND_TYPE_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        nameIndex = reader.readU2();
        descriptorIndex = reader.readU2();
    }

    @Override
    public String toString() {
        return "<CONSTANT_NameAndType_Info, #" + nameIndex + " #" + descriptorIndex + ">";
    }
}

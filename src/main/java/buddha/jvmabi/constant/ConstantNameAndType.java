package buddha.jvmabi.constant;

import buddha.jvmabi.ClassFileConstantTagConst;
import buddha.jvmabi.annotation.U1;
import buddha.jvmabi.annotation.U2;
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
public final class ConstantNameAndType implements Serializable, IJvmConstant {
    private @U2 int nameIndex;       // 指向常量池中一个名称常量
    private @U2 int descriptorIndex; // 指向常量池中一个描述符常量

    public ConstantNameAndType(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.NAME_AND_TYPE_INFO; }

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

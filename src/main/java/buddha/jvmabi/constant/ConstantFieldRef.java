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
public final class ConstantFieldRef implements Serializable, IJvmConstant {

    private @U2 int classIndex;       // 指向常量池中一个CONSTANT_Class_Info
    private @U2 int nameAndTypeIndex; // 指向常量池中一个CONSTANT_NameAndType_Info

    public ConstantFieldRef(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.FIELD_REF_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    @Override
    public String toString() {
        return "<CONSTANT_Fieldref_Info, #" + classIndex + " #" + nameAndTypeIndex + ">";
    }
}

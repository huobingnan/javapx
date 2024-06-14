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
public final class ConstantInterfaceMethodRef implements Serializable, IJvmConstant {

    private @U2 int classIndex;       // 指向常量池中的一个CONSTANT_Class_Info
    private @U2 int nameAndTypeIndex; // 指向常量池中的一个CONSTANT_NameAndType_Info

    public ConstantInterfaceMethodRef(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.INTERFACE_METHOD_REF_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    @Override
    public String toString() {
        return "<CONSTANT_Interfacemethod_Info, #" + classIndex + " #" + nameAndTypeIndex + ">";
    }
}

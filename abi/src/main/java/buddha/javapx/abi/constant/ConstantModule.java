package buddha.javapx.abi.constant;

import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import buddha.javapx.abi.annotation.U2;
import buddha.javapx.abi.reader.IByteCodeReader;
import lombok.Getter;

import java.io.Serializable;


@Getter
public final class ConstantModule implements Serializable, IJvmConstant {

    private @U2 int nameIndex; // 指向常量池中一个CONSTANT_Utf8_Info

    public ConstantModule() {}

    public ConstantModule(short nameIndex) { this.nameIndex = nameIndex; }

    public ConstantModule(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.MODULE_INFO; }

    @Override
    public void read(IByteCodeReader reader) { nameIndex = reader.readU2(); }

    @Override
    public String toString() {
        return "<CONSTANT_Module_Info, #" + nameIndex + ">";
    }
}

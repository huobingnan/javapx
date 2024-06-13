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
public final class ConstantInvokeDynamic implements Serializable, IJvmConstant {

    private @U2 int bootstrapMethodAttributeIndex;
    private @U2 int nameAndTypeIndex;

    public ConstantInvokeDynamic(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.INVOKE_DYNAMIC_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bootstrapMethodAttributeIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    @Override
    public String toString() {
        return "<CONSTANT_InvokeDynamic_Info, @" + bootstrapMethodAttributeIndex + " #" + nameAndTypeIndex + ">";
    }
}

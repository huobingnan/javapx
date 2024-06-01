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
public final class ConstantMethodHandle implements Serializable, IJvmConstant {
    private @U2 int referenceKind;
    private @U2 int referenceIndex;

    public ConstantMethodHandle(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.METHOD_HANDLE_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        referenceKind = reader.readU2();
        referenceIndex = reader.readU2();
    }

    @Override
    public String toString() {
        return "<CONSTANT_Methodhandle_Info, " + referenceKind + " #" + referenceIndex + ">";
    }
}

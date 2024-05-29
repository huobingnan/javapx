package buddha.jvmabi.constant;

import buddha.jvmabi.JvmClassFileConstantEnum;
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
    private short referenceKind;
    private short referenceIndex;

    public ConstantMethodHandle(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.METHOD_HANDLE_INFO; }

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

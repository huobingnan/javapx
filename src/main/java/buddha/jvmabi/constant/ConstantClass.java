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
public final class ConstantClass implements Serializable, IJvmConstant {
    public short nameIndex;

    public ConstantClass(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.CLASS_INFO; }

    @Override
    public void read(IByteCodeReader reader) { nameIndex = reader.readU2(); }

    @Override
    public String toString() {
        return "<CONSTANT_Class_Info, #" + nameIndex + ">";
    }
}

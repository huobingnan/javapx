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
public final class ConstantLong implements Serializable, IJvmConstant {
    private long value;

    public ConstantLong(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.LONG_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = reader.readU8(); }

    @Override
    public String toString() {
        return "<CONSTANT_Long_Info, " + value + ">";
    }
}

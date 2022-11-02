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
public final class ConstantInteger implements Serializable, IJvmConstant {
    private int value;

    public ConstantInteger(IByteCodeReader reader) { read(reader); }
    @Override
    public byte getTag() { return JvmClassFileConstantEnum.INTEGER_INFO; }

    @Override
    public void read(IByteCodeReader reader) { value = reader.readU4(); }

    @Override
    public String toString() {
        return "<CONSTANT_Integer_Info, " + value + ">";
    }
}

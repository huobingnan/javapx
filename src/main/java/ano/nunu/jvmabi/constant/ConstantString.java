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
public final class ConstantString implements Serializable, IJvmConstant {
    private short index;

    public ConstantString(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return JvmClassFileConstantEnum.STRING_INFO; }

    @Override
    public void read(IByteCodeReader reader) { index = reader.readU2(); }

    @Override
    public String toString() {
        return "<CONSTANT_String_Info, #" + index + ">";
    }
}

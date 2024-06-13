package buddha.jvmabi.constant;

import buddha.jvmabi.ClassFileConstantTagConst;
import buddha.jvmabi.annotation.U1;
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
public final class ConstantDouble implements Serializable, IJvmConstant {
    private double value;

    public ConstantDouble(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.DOUBLE_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        value = Double.longBitsToDouble(reader.readU8());
    }

    @Override
    public String toString() {
       return "<CONSTANT_Double_Info, " + value + ">";
    }
}

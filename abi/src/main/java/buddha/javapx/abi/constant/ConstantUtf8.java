package buddha.javapx.abi.constant;

import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import buddha.javapx.abi.reader.IByteCodeReader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class ConstantUtf8 implements Serializable, IJvmConstant {
    private @U1 byte[] bytes;

    public ConstantUtf8(IByteCodeReader reader) { read(reader); }

    @U1
    @Override
    public int getTag() { return ClassFileConstantTagConst.UTF8_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bytes = new byte[reader.readU2()];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = (byte) reader.readU1();
    }

    public String contentToString() { return new String(bytes, StandardCharsets.UTF_8); }

    @Override
    public String toString() {
        return "<CONSTANT_Utf8_Info, " + new String(bytes, StandardCharsets.UTF_8) + ">";
    }
}

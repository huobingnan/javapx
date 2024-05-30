package buddha.jvmabi.constant;

import buddha.jvmabi.ClassFileConstantTagConst;
import buddha.jvmabi.reader.IByteCodeReader;
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
    private byte[] bytes;

    public ConstantUtf8(IByteCodeReader reader) { read(reader); }

    @Override
    public byte getTag() { return ClassFileConstantTagConst.UTF8_INFO; }

    @Override
    public void read(IByteCodeReader reader) {
        bytes = new byte[reader.readU2()];
        for (int i = 0; i < bytes.length; i++)
            bytes[i] = reader.readU1();
    }

    public String contentToString() { return new String(bytes, StandardCharsets.UTF_8); }

    @Override
    public String toString() {
        return "<CONSTANT_Utf8_Info, " + new String(bytes, StandardCharsets.UTF_8) + ">";
    }
}

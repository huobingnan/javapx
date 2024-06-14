package buddha.javapx.abi.attribute;

import buddha.javapx.abi.AttributeType;
import buddha.javapx.abi.ClassFileConstantPool;
import buddha.javapx.abi.annotation.U2;
import buddha.javapx.abi.annotation.U4;
import buddha.javapx.abi.reader.IByteCodeReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public final class ConstantValueAttr implements Serializable, IJvmAttribute {

    private @U4 int length;
    private @U2 int constantValueIndex;

    public ConstantValueAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.CONSTANT_VALUE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        constantValueIndex = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length; }

}

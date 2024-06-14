package buddha.javapx.abi.attribute;

import buddha.javapx.abi.AttributeType;
import buddha.javapx.abi.reader.IByteCodeReader;
import buddha.javapx.abi.ClassFileConstantPool;

import java.io.Serializable;

public final class SyntheticAttr implements Serializable, IJvmAttribute {

    public SyntheticAttr() {}

    public SyntheticAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.SYNTHETIC; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {

    }

    @Override
    public int getAttrLength() { return 0; }
}

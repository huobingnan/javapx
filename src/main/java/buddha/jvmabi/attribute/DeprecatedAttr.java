package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;

import java.io.Serializable;

public final class DeprecatedAttr implements Serializable, IJvmAttribute {

    public DeprecatedAttr() {}

    public DeprecatedAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.DEPRECATED; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) { }

    @Override
    public int getAttrLength() { return 0; }
}

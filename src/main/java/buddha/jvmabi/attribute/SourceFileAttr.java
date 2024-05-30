package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;

import java.io.Serializable;

public final class SourceFileAttr implements Serializable, IJvmAttribute {

    private int length;
    private short index;

    public SourceFileAttr() {}

    public SourceFileAttr(int length, short index) {
        this.length = length;
        this.index = index;
    }

    public SourceFileAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.SOURCE_FILE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        index  = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length;}

    public short getIndex() { return index; }
}

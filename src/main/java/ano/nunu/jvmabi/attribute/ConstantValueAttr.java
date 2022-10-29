package ano.nunu.jvmabi.attribute;

import ano.nunu.jvmabi.JvmClassFileConstantPool;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class ConstantValueAttr implements Serializable, IJvmAttribute {

    private int length;
    private short constantValueIndex;

    public ConstantValueAttr() {}

    public ConstantValueAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "ConstantValue"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        constantValueIndex = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length; }


    public short getConstantValueIndex() { return constantValueIndex; }
}

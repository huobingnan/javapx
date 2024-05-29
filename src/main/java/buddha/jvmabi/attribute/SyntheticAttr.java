package buddha.jvmabi.attribute;

import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.JvmClassFileConstantPool;

import java.io.Serializable;

public final class SyntheticAttr implements Serializable, IJvmAttribute {

    public SyntheticAttr() {}

    public SyntheticAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "Synthetic"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {

    }

    @Override
    public int getAttrLength() { return 0; }
}

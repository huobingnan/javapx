package ano.nunu.jvmabi.attribute;

import ano.nunu.jvmabi.reader.IByteCodeReader;
import ano.nunu.jvmabi.JvmClassFileConstantPool;

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

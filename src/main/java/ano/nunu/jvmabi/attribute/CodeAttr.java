package ano.nunu.jvmabi.attribute;

import ano.nunu.jvmabi.JvmClassFileConstantPool;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class CodeAttr implements Serializable, IJvmAttribute {

    @Override
    public String name() { return "Code"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {

    }

    @Override
    public int getAttrLength() { return 0; }
}

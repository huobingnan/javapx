package ano.nunu.jvmabi.attribute;

import ano.nunu.jvmabi.reader.IByteCodeReader;
import ano.nunu.jvmabi.JvmClassFileConstantPool;

public interface IJvmAttribute {

    String name();

    void read(JvmClassFileConstantPool pool, IByteCodeReader reader);

    int getAttrLength();
}

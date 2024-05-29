package buddha.jvmabi.attribute;

import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.JvmClassFileConstantPool;

public interface IJvmAttribute {

    String name();

    void read(JvmClassFileConstantPool pool, IByteCodeReader reader);

    int getAttrLength();
}

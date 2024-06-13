package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;

public interface IJvmAttribute {

    AttributeType type();

    void read(ClassFileConstantPool pool, IByteCodeReader reader);

    int getAttrLength();
}

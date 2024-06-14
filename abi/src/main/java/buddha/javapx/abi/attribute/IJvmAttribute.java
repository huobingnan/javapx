package buddha.javapx.abi.attribute;

import buddha.javapx.abi.AttributeType;
import buddha.javapx.abi.reader.IByteCodeReader;
import buddha.javapx.abi.ClassFileConstantPool;

public interface IJvmAttribute {

    AttributeType type();

    void read(ClassFileConstantPool pool, IByteCodeReader reader);

    int getAttrLength();
}

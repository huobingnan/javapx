package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.ClassFileConstantPool;
import buddha.jvmabi.reader.IByteCodeReader;

public class MethodParametersAttr implements IJvmAttribute {
    private int length;
    private MethodParameter[] parameters;

    public MethodParametersAttr() {}

    public MethodParametersAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.METHOD_PARAMETERS; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        parameters = new MethodParameter[reader.readU1()];
        for (int i = 0; i < parameters.length; i++) {
            final short nameIndex = reader.readU2();
            final short accessFlags = reader.readU2();
            parameters[i] = new MethodParameter(nameIndex, accessFlags);
        }

    }

    @Override
    public int getAttrLength() { return length; }
}

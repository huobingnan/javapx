package buddha.jvmabi.attribute;

import buddha.jvmabi.JvmClassFileConstantPool;
import buddha.jvmabi.reader.IByteCodeReader;

public class MethodParametersAttr implements IJvmAttribute {
    private int length;
    private MethodParameter[] parameters;

    public MethodParametersAttr() {}

    public MethodParametersAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "MethodParameters"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
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

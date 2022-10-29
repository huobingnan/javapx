package ano.nunu.jvmabi.attribute;

import ano.nunu.jvmabi.JvmClassFile;
import ano.nunu.jvmabi.JvmClassFileConstantPool;
import ano.nunu.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class CodeAttr implements Serializable, IJvmAttribute {

    private int length;
    private short maxStack;
    private short maxLocals;
    private byte[] code;
    private CodeExceptionInfo[] exceptionTable;
    private IJvmAttribute[] attributes;

    public CodeAttr() {}

    public CodeAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "Code"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        code = new byte[reader.readU4()];
        for (int i = 0; i < code.length; i++) { code[i] = reader.readU1(); }
        exceptionTable = new CodeExceptionInfo[reader.readU2()];
        for (int i = 0; i < exceptionTable.length; i++) {
            exceptionTable[i] = new CodeExceptionInfo(reader.readU2(),
                    reader.readU2(), reader.readU2(), reader.readU2());
        }
        attributes = new IJvmAttribute[reader.readU2()];

        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = JvmClassFile.parseAttribute(pool, reader);
        }
    }

    @Override
    public int getAttrLength() { return 0; }
}

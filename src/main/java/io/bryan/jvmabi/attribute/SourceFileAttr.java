package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class SourceFileAttr implements Serializable, IJvmAttribute {

    private int length;
    private short index;

    public SourceFileAttr() {}

    public SourceFileAttr(int length, short index) {
        this.length = length;
        this.index = index;
    }

    public SourceFileAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "SourceFile"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        index  = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length;}

    public short getIndex() { return index; }
}

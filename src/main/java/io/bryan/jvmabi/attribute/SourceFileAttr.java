package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class SourceFileAttr implements Serializable, IAttribute {

    private int length;
    private short index;

    public SourceFileAttr() {}

    public SourceFileAttr(int length, short index) {
        this.length = length;
        this.index = index;
    }

    public SourceFileAttr(JvmClassFile classFile, IByteCodeReader reader) { read(classFile, reader); }

    @Override
    public String name() { return "SourceFile"; }

    @Override
    public void read(JvmClassFile classFile, IByteCodeReader reader) {
        length = reader.readU4();
        index  = reader.readU2();
    }
}

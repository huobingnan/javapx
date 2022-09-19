package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class LineNumberTableAttr implements Serializable, IJvmAttribute {

    private int length;
    private LineNumberInfo[] lineNumberTable;

    public LineNumberTableAttr() {}

    public LineNumberTableAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "LineNumberTable"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        lineNumberTable = new LineNumberInfo[reader.readU2()];
        for (int i = 0; i < lineNumberTable.length; i++) {
            final LineNumberInfo lineNumberInfo = new LineNumberInfo();
            lineNumberInfo.setStartPc(reader.readU2());
            lineNumberInfo.setLineNumber(reader.readU2());
            lineNumberTable[i] = lineNumberInfo;
        }
    }

    @Override
    public int getAttrLength() { return length; }

    public int length() { return lineNumberTable.length; }

    public LineNumberInfo get(int index) { return lineNumberTable[index]; }
}

package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

// 本地变量表
public final class LocalVariableTableAttr implements Serializable, IJvmAttribute {

    private int length;
    private LocalVariableInfo[] localVariableTable;

    public LocalVariableTableAttr() {}

    public LocalVariableTableAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "LocalVariableTable"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        localVariableTable = new LocalVariableInfo[reader.readU2()];
        for (int i = 0; i < localVariableTable.length; i++) {
            final LocalVariableInfo localVariableInfo = new LocalVariableInfo();
            localVariableInfo.setStartPc(reader.readU2());
            localVariableInfo.setLength(reader.readU2());
            localVariableInfo.setNameIndex(reader.readU2());
            localVariableInfo.setDescriptorIndex(reader.readU2());
            localVariableInfo.setIndex(reader.readU2());
            localVariableTable[i] = localVariableInfo;
        }
    }

    public LocalVariableInfo get(int index) { return localVariableTable[index]; }

    public int length() { return localVariableTable.length; }

    public int getAttrLength() { return length; }
}

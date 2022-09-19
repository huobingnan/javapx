package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

// Exceptions属性会列举出方法中抛出的受检查异常项（throws后跟着的异常）
public final class ExceptionsAttr implements Serializable, IJvmAttribute {

    private int length;
    private short[] exceptionsIndex;

    public ExceptionsAttr() {}

    public ExceptionsAttr(int length, short[] exceptionsIndex) {
        this.length = length;
        this.exceptionsIndex = exceptionsIndex;
    }

    public ExceptionsAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "Exceptions"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        exceptionsIndex = new short[reader.readU2()];
        for (int i = 0; i < exceptionsIndex.length; i++)
            exceptionsIndex[i] = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length; }
}

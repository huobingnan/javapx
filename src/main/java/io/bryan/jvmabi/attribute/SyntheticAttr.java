package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class SyntheticAttr implements Serializable, IJvmAttribute {

    public SyntheticAttr() {}

    public SyntheticAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "Synthetic"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {

    }

    @Override
    public int getAttrLength() { return 0; }
}

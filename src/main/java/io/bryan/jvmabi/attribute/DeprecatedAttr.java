package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class DeprecatedAttr implements Serializable, IJvmAttribute {

    public DeprecatedAttr() {}

    public DeprecatedAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "Deprecated"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) { }

    @Override
    public int getAttrLength() { return 0; }
}

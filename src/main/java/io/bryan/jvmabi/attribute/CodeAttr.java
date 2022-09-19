package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.JvmClassFileConstantPool;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class CodeAttr implements Serializable, IJvmAttribute {

    @Override
    public String name() { return "Code"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {

    }

    @Override
    public int getAttrLength() { return 0; }
}

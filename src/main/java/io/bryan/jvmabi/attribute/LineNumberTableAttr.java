package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.reader.IByteCodeReader;

import java.io.Serializable;

public final class LineNumberTableAttr implements Serializable, IJvmAttribute {

    @Override
    public String name() { return "LineNumberTable"; }

    @Override
    public void read(JvmClassFile classFile, IByteCodeReader reader) {

    }
}

package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.reader.IByteCodeReader;

public interface IAttribute {

    String name();

    void read(JvmClassFile classFile, IByteCodeReader reader);
}

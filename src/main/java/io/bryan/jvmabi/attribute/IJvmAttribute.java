package io.bryan.jvmabi.attribute;

import io.bryan.jvmabi.JvmClassFile;
import io.bryan.jvmabi.reader.IByteCodeReader;

public interface IJvmAttribute {

    String name();

    void read(JvmClassFile classFile, IByteCodeReader reader);
}

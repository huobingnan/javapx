package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.annotation.U4;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;

import java.io.Serializable;

// Exceptions属性会列举出方法中抛出的受检查异常项（throws后跟着的异常）
public final class ExceptionsAttr implements Serializable, IJvmAttribute {

    private @U4 int length;
    private @U2 int[] exceptionsIndex;

    public ExceptionsAttr() {}

    public ExceptionsAttr(int length, int[] exceptionsIndex) {
        this.length = length;
        this.exceptionsIndex = exceptionsIndex;
    }

    public ExceptionsAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.EXCEPTIONS; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        exceptionsIndex = new int[reader.readU2()];
        for (int i = 0; i < exceptionsIndex.length; i++)
            exceptionsIndex[i] = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length; }
}

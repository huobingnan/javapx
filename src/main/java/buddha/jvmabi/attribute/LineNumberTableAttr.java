package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.annotation.U4;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;

import java.io.Serializable;

public final class LineNumberTableAttr implements Serializable, IJvmAttribute {

    private @U4 int length;
    private LineNumberInfo[] lineNumberTable;


    public LineNumberTableAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.LINE_NUMBER_TABLE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
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

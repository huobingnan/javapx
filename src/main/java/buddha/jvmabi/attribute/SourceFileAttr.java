package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.annotation.U4;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class SourceFileAttr implements Serializable, IJvmAttribute {

    private @U4 int length;
    private @U2 int index;

    public SourceFileAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.SOURCE_FILE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        index  = reader.readU2();
    }

    @Override
    public int getAttrLength() { return length;}

}

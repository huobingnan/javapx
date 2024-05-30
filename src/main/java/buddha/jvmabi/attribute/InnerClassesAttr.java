package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.ClassFileConstantPool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

// 记录内部类的信息
@Getter
@Setter
@NoArgsConstructor
public final class InnerClassesAttr implements Serializable, IJvmAttribute {

    private int length;
    private InnerClassInfo[] classesInfo;

    public InnerClassesAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.INNER_CLASSES; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        classesInfo = new InnerClassInfo[reader.readU2()];
        for (int i = 0; i < classesInfo.length; i++) {
            final InnerClassInfo classInfo = new InnerClassInfo();
            classInfo.setInnerClassInfoIndex(reader.readU2());
            classInfo.setOuterClassInfoIndex(reader.readU2());
            classInfo.setInnerNameIndex(reader.readU2());
            classInfo.setInnerClassAccessFlags(reader.readU2());
            classesInfo[i] = classInfo;
        }
    }

    @Override
    public int getAttrLength() { return length; }

}

package buddha.jvmabi.attribute;

import buddha.jvmabi.reader.IByteCodeReader;
import buddha.jvmabi.JvmClassFileConstantPool;
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

    public InnerClassesAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "InnerClasses"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
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

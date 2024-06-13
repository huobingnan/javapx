package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.ClassFile;
import buddha.jvmabi.ClassFileConstantPool;
import buddha.jvmabi.annotation.U1;
import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.reader.IByteCodeReader;
import lombok.*;

import java.io.Serializable;

/**
 * 代码属性
 * @author BRYAN
 */
@Getter
@Setter
@NoArgsConstructor
public final class CodeAttr implements Serializable, IJvmAttribute {

    private @U2 int length;
    private @U2 int maxStack;
    private @U2 int maxLocals;
    private @U1 byte[] code;
    private CodeExceptionInfo[] exceptionTable;
    private IJvmAttribute[] attributes;

    public CodeAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.CODE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        code = new byte[reader.readU4()];
        for (int i = 0; i < code.length; i++) { code[i] = (byte)reader.readU1(); }
        exceptionTable = new CodeExceptionInfo[reader.readU2()];
        for (int i = 0; i < exceptionTable.length; i++) {
            exceptionTable[i] = new CodeExceptionInfo(reader.readU2(),
                    reader.readU2(), reader.readU2(), reader.readU2());
        }
        attributes = new IJvmAttribute[reader.readU2()];

        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = ClassFile.parseAttribute(pool, reader);
        }
    }

    @Override
    public int getAttrLength() { return 0; }
}

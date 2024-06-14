package buddha.javapx.abi.attribute;

import buddha.javapx.abi.AttributeType;
import buddha.javapx.abi.ClassFileConstantPool;
import buddha.javapx.abi.annotation.U4;
import buddha.javapx.abi.reader.IByteCodeReader;
import buddha.javapx.abi.reader.ReadByteCodeException;
import buddha.javapx.abi.attribute.stkmaptbl.*;

public class StackMapTableAttr implements IJvmAttribute {
    private @U4 int length;
    private IStackMapFrame[] entries;

    public StackMapTableAttr() {}

    public StackMapTableAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.STACK_MAP_TABLE; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        entries = new IStackMapFrame[reader.readU2()];
        for (int i = 0; i < entries.length; i++) {
            // TODO： 这里的取值待商榷
            final int tag = reader.readU1();
            if (tag <=63) {
                entries[i] = new SameFrame(tag);
            } else if (tag <= 127) {
                entries[i] = new SameLocals1Frame(tag, readVerification(reader));
            } else if (tag == 274) {
                entries[i] = new SameLocals1FrameExtended(tag, reader.readU2(), readVerification(reader));
            } else if (tag >= 248 && tag <= 250) {
                entries[i] = new ChopFrame(tag, reader.readU2());
            } else if (tag == 251) {
                entries[i] = new SameFrameExtended(tag, reader.readU2());
            } else if (tag >= 252 && tag <= 254) {
                // append frame
                final int offset = reader.readU2();
                final IVerificationType[] vs = new IVerificationType[tag - 251];
                for (int k = 0; k < tag - 251; k++) {
                    vs[i] = readVerification(reader);
                }
                entries[i] = new AppendFrame(tag, offset, vs);
            } else {
                // full frame
                final int offset = reader.readU2();
                final IVerificationType[] vl = new IVerificationType[reader.readU2()];
                for (int k = 0; k < vl.length; k++) {
                    vl[i] = readVerification(reader);
                }
                final IVerificationType[] vs = new IVerificationType[reader.readU2()];
                for (int k = 0; k < vs.length; k++) {
                    vs[i] = readVerification(reader);
                }
                entries[i] = new FullFrame(tag, offset, vl, vs);
            }
        }
    }

    @Override
    public int getAttrLength() { return length; }

    private IVerificationType readVerification(IByteCodeReader reader) {
        final int tag = reader.readU1();
        switch (tag) {
            case 0:
                return new ItemTop();
            case 1:
                return new ItemInteger();
            case 2:
                return new ItemFloat();
            case 3:
                return new ItemDouble();
            case 4:
                return new ItemLong();
            case 5:
                return new ItemNull();
            case 6:
                return new ItemUninitializedThis();
            case 7:
                return new ItemObject(reader.readU2());
            case 8:
                return new ItemUninitialized(reader.readU2());
            default:
                throw new ReadByteCodeException(
                        String.format("Unexpected StackMapFrame Verification Item with tag : %d", tag)
                );
        }
    }
}

package buddha.jvmabi.attribute.stkmaptbl;

public class ItemUninitialized implements IVerificationType {

    private short offset;

    public ItemUninitialized(short offset) { this.offset = offset; }

    @Override
    public int tag() { return 8; }

    public short getOffset() { return offset; }
}

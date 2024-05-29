package buddha.jvmabi.attribute.stkmaptbl;

public class ItemObject implements IVerificationType {

    private short poolIndex;

    public ItemObject(short poolIndex) { this.poolIndex = poolIndex; }

    @Override
    public int tag() { return 7; }

    public short getPoolIndex() { return poolIndex; }
}

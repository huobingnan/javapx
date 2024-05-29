package buddha.jvmabi.attribute.stkmaptbl;

public class ChopFrame implements IStackMapFrame {
    private int tag;
    private short offsetDelta;

    public ChopFrame(int tag, short offsetDelta) {
        this.tag = tag;
        this.offsetDelta = offsetDelta;
    }

    @Override
    public int tag() { return tag; }
}

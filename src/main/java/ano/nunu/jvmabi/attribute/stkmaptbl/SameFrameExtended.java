package ano.nunu.jvmabi.attribute.stkmaptbl;

public class SameFrameExtended implements IStackMapFrame {
    private int tag;
    private short offsetDelta;

    public SameFrameExtended(int tag, short offsetDelta) {
        this.tag = tag;
        this.offsetDelta = offsetDelta;
    }

    @Override
    public int tag() { return tag; }
}

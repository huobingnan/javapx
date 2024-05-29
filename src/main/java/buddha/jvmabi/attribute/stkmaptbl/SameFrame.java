package buddha.jvmabi.attribute.stkmaptbl;

public class SameFrame implements IStackMapFrame {

    private int tag;

    public SameFrame(int tag) { this.tag = tag; }

    @Override
    public int tag() { return tag; }
}

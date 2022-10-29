package ano.nunu.jvmabi.attribute.stkmaptbl;

public class SameLocals1FrameExtended implements IStackMapFrame {

    private int tag;
    private short offsetDelta;
    private IVerificationType verification;

    public SameLocals1FrameExtended(int tag, short offsetDelta, IVerificationType verification) {
        this.tag = tag;
        this.offsetDelta = offsetDelta;
        this.verification = verification;
    }

    @Override
    public int tag() { return tag; }
}

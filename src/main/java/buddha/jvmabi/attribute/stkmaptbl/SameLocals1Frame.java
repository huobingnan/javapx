package buddha.jvmabi.attribute.stkmaptbl;

public class SameLocals1Frame implements IStackMapFrame {
    private int tag;

    private IVerificationType verification;

    public SameLocals1Frame(int tag, IVerificationType verification) {
        this.tag = tag;
        this.verification = verification;
    }

    @Override
    public int tag() { return tag; }


}

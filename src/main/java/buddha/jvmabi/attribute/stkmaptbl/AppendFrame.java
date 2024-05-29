package buddha.jvmabi.attribute.stkmaptbl;

public class AppendFrame implements IStackMapFrame {
    private int tag;
    private short offsetDelta;
    private IVerificationType[] verifications;

    public AppendFrame(int tag, short offsetDelta, IVerificationType[] verifications) {
        this.tag = tag;
        this.offsetDelta = offsetDelta;
        this.verifications = verifications;
    }

    @Override
    public int tag() { return tag; }
}

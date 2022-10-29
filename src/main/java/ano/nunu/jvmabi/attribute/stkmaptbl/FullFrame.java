package ano.nunu.jvmabi.attribute.stkmaptbl;

public class FullFrame implements IStackMapFrame {
    private int tag;
    private short offset;
    private IVerificationType[] vl;
    private IVerificationType[] vs;

    public FullFrame(int tag, short offset, IVerificationType[] vl, IVerificationType[] vs) {
        this.tag = tag;
        this.offset = offset;
        this.vl = vl;
        this.vs = vs;
    }

    @Override
    public int tag() {
        return 0;
    }
}

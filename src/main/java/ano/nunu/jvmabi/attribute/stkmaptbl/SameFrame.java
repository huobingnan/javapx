package ano.nunu.jvmabi.attribute.stkmaptbl;

import ano.nunu.jvmabi.attribute.stkmaptbl.IStackMapFrame;

public class SameFrame implements IStackMapFrame {

    private int tag;

    public SameFrame(int tag) { this.tag = tag; }

    @Override
    public int tag() { return tag; }
}

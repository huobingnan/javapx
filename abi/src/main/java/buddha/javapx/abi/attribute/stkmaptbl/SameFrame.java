package buddha.javapx.abi.attribute.stkmaptbl;

import buddha.javapx.abi.annotation.U1;

public class SameFrame implements IStackMapFrame {

    private @U1 int tag;

    public SameFrame(int tag) { this.tag = tag; }

    @Override
    public int tag() { return tag; }
}

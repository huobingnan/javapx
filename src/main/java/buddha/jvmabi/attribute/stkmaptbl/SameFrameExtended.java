package buddha.jvmabi.attribute.stkmaptbl;

import buddha.jvmabi.annotation.U1;
import buddha.jvmabi.annotation.U2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SameFrameExtended implements IStackMapFrame {
    private @U1 int tag;
    private @U2 int offsetDelta;

    @Override
    public int tag() { return tag; }
}

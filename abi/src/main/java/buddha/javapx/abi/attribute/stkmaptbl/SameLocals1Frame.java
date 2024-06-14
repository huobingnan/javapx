package buddha.javapx.abi.attribute.stkmaptbl;

import buddha.javapx.abi.annotation.U1;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SameLocals1Frame implements IStackMapFrame {
    private @U1 int tag;

    private IVerificationType verification;

    public SameLocals1Frame(int tag, IVerificationType verification) {
        this.tag = tag;
        this.verification = verification;
    }

    @U1
    @Override
    public int tag() { return tag; }


}

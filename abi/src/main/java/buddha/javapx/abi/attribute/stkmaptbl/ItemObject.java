package buddha.javapx.abi.attribute.stkmaptbl;

import buddha.javapx.abi.annotation.U2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemObject implements IVerificationType {

    private @U2 int poolIndex;

    @Override
    public int tag() { return 7; }

}

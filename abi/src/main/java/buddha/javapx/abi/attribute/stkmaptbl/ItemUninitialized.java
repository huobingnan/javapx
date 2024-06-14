package buddha.javapx.abi.attribute.stkmaptbl;

import buddha.javapx.abi.annotation.U2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemUninitialized implements IVerificationType {

    private @U2 int offset;

    @Override
    public int tag() { return 8; }

}

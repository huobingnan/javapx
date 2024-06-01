package buddha.jvmabi.attribute.stkmaptbl;

import buddha.jvmabi.annotation.U2;
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

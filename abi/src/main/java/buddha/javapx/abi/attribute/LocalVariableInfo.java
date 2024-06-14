package buddha.javapx.abi.attribute;

import buddha.javapx.abi.annotation.U2;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class LocalVariableInfo implements Serializable {
    private @U2 int startPc;
    private @U2 int length;
    private @U2 int nameIndex;
    private @U2 int descriptorIndex;
    private @U2 int index;
}

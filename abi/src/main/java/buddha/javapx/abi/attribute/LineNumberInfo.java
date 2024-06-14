package buddha.javapx.abi.attribute;

import buddha.javapx.abi.annotation.U2;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public final class LineNumberInfo implements Serializable {
    private @U2 int startPc;
    private @U2 int lineNumber;

}

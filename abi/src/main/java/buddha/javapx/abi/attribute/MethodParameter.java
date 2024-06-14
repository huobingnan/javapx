package buddha.javapx.abi.attribute;

import buddha.javapx.abi.annotation.U2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public final class MethodParameter {
    private @U2 int nameIndex;
    private @U2 int accessFlags;

}

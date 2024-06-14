package buddha.javapx.abi.field;

import buddha.javapx.abi.attribute.IJvmAttribute;
import buddha.javapx.abi.annotation.U2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class FieldItem {
    private @U2 int accessFlags;
    private @U2 int nameIndex;
    private @U2 int descriptorIndex;
    private IJvmAttribute[] attributes;

}

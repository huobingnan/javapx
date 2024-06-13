package buddha.jvmabi.field;

import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.attribute.IJvmAttribute;
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

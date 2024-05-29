package buddha.jvmabi.field;

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
    private short accessFlags;
    private short nameIndex;
    private short descriptorIndex;
    private IJvmAttribute[] attributes;

}

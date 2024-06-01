package buddha.jvmabi.method;

import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.attribute.IJvmAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * 方法条目
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class MethodItem {
    private @U2 int accessFlags;
    private @U2 int nameIndex;
    private @U2 int descriptorIndex;
    private IJvmAttribute[] attributes;
}

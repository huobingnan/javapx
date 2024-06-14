package buddha.javapx.abi.method;

import buddha.javapx.abi.attribute.IJvmAttribute;
import buddha.javapx.abi.annotation.U2;
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

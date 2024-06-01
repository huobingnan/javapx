package buddha.jvmabi.attribute;

import buddha.jvmabi.annotation.U2;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public final class InnerClassInfo implements Serializable {

    private @U2 int innerClassInfoIndex;
    private @U2 int outerClassInfoIndex;
    private @U2 int innerNameIndex;
    private @U2 int innerClassAccessFlags;

}

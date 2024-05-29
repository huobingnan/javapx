package buddha.jvmabi.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CodeExceptionInfo {
    private short startPC;
    private short endPC;
    private short handlerPC;
    private short catchType;
}

package buddha.jvmabi.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 代码异常表
 * @author BRYAN
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public final class CodeExceptionInfo {
    /** 起始PC */
    private short startPC;
    /** 结束PC */
    private short endPC;
    /** 处理PC */
    private short handlerPC;
    /** 捕获的异常类型 */
    private short catchType;
}

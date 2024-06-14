package buddha.javapx.abi.attribute;

import buddha.javapx.abi.annotation.U2;
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
    private @U2 int startPC;
    /** 结束PC */
    private @U2 int endPC;
    /** 处理PC */
    private @U2 int handlerPC;
    /** 捕获的异常类型 */
    private @U2 int catchType;
}

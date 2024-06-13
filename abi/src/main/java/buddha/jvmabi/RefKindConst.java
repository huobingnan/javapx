package buddha.jvmabi;

import buddha.jvmabi.annotation.U1;

/**
 * 对应常量池中CONSTANT_MethodHandle_info类型常量的refKind属性
 * @see buddha.jvmabi.constant.ConstantMethodHandle
 * @author BRYAN
 */
public interface RefKindConst {
    @U1 int GET_FIELD              = 1;
    @U1 int GET_STATIC             = 2;
    @U1 int PUT_FIELD              = 3;
    @U1 int PUT_STATIC             = 4;
    @U1 int INVOKE_VIRTUAL         = 5;
    @U1 int INVOKE_STATIC          = 6;
    @U1 int INVOKE_SPECIAL         = 7;
    @U1 int NEW_INVOKE_SPECIAL     = 8;
    @U1 int INVOKE_INTERFACE       = 9;
}

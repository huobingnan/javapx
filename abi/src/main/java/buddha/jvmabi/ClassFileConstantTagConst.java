package buddha.jvmabi;

import buddha.jvmabi.annotation.U1;
import buddha.jvmabi.annotation.U2;

public interface ClassFileConstantTagConst {
    @U1 int UTF8_INFO                 = 1;
    @U1 int INTEGER_INFO              = 3;
    @U1 int FLOAT_INFO                = 4;
    @U1 int LONG_INFO                 = 5;
    @U1 int DOUBLE_INFO               = 6;
    @U1 int CLASS_INFO                = 7;
    @U1 int STRING_INFO               = 8;
    @U1 int FIELD_REF_INFO            = 9;
    @U1 int  METHOD_REF_INFO          = 10;
    @U1 int INTERFACE_METHOD_REF_INFO = 11;
    @U1 int NAME_AND_TYPE_INFO        = 12;
    @U1 int METHOD_HANDLE_INFO        = 15;
    @U1 int METHOD_TYPE_INFO          = 16;
    @U1 int DYNAMIC_INFO              = 17;
    @U1 int INVOKE_DYNAMIC_INFO       = 18;
    @U1 int MODULE_INFO               = 19;
    @U1 int PACKAGE_INFO              = 20;
}

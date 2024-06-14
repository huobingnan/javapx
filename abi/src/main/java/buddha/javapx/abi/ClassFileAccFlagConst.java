package buddha.javapx.abi;

import buddha.javapx.abi.annotation.U2;

public interface ClassFileAccFlagConst {
    @U2 int ACC_PUBLIC       = 0x0001;
    @U2 int ACC_FINAL        = 0x0010;
    @U2 int ACC_SUPER        = 0x0020;
    @U2 int ACC_INTERFACE    = 0x0200;
    @U2 int ACC_ABSTRACT     = 0x0400;
    @U2 int ACC_SYNTHETIC    = 0x1000;
    @U2 int ACC_ANNOTATION   = 0x2000;
    @U2 int ACC_ENUM         = 0x4000;
    @U2 int  ACC_MODULE      = 0x8000;

    @U2 int ACC_PRIVATE      = 0x0002;
    @U2 int ACC_PROTECTED    = 0x0004;
    @U2 int ACC_VOLATILE     = 0x0040;
    @U2 int ACC_TRANSIENT    = 0x0080;
    @U2 int ACC_STATIC       = 0x0008;
    @U2 int ACC_SYNCHRONIZED = 0x0020;
    @U2 int ACC_BRIDGE       = 0x0040;
    @U2 int ACC_VARARGS      = 0x0080;
    @U2 int ACC_NATIVE       = 0x0100;
    @U2 int ACC_STRICT       = 0x8000;
}

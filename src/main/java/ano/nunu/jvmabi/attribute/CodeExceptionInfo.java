package ano.nunu.jvmabi.attribute;

public final class CodeExceptionInfo {
    private short startPC, endPC, handlerPC, catchType;

    public CodeExceptionInfo(short startPC, short endPC, short handlerPC, short catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
    }
}

package ano.nunu.jvmabi.attribute;

import java.io.Serializable;

public final class LineNumberInfo implements Serializable {
    private short startPc;
    private short lineNumber;

    public short getStartPc() { return startPc; }

    public short getLineNumber() { return lineNumber; }

    public void setStartPc(short startPc) { this.startPc = startPc; }

    public void setLineNumber(short lineNumber) { this.lineNumber = lineNumber; }
}

package ano.nunu.jvmabi.attribute;

public class BootstrapMethod {
    private short methodRef;
    private short[] args; // args type which will depict in constant pool.

    public BootstrapMethod(short methodRef, short[] args) {
        this.methodRef = methodRef;
        this.args = args;
    }

    public short getMethodRef() { return methodRef; }

    public void setMethodRef(short methodRef) { this.methodRef = methodRef; }

    public short[] getArgs() { return args; }

    public void setArgs(short[] args) { this.args = args; }
}

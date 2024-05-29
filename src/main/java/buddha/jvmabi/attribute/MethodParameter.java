package buddha.jvmabi.attribute;

public final class MethodParameter {
    private short nameIndex;
    private short accessFlags;

    public MethodParameter(short nameIndex, short accessFlags) {
        this.nameIndex = nameIndex;
        this.accessFlags = accessFlags;
    }

    public short getNameIndex() { return nameIndex; }

    public void setNameIndex(short nameIndex) { this.nameIndex = nameIndex; }

    public short getAccessFlags() { return accessFlags; }

    public void setAccessFlags(short accessFlags) { this.accessFlags = accessFlags; }
}

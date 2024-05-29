package buddha.jvmabi;

import buddha.jvmabi.method.MethodItem;

public final class JvmClassFileMethodTable {
    private final MethodItem[] methods;
    private int appendCursor;

    public JvmClassFileMethodTable(int count) {
        methods = new MethodItem[count];
        appendCursor = 0;
    }

    public int length() { return methods.length; }

    public void append(MethodItem item) { methods[appendCursor++] = item; }

    public MethodItem get(int idx) { return methods[idx]; }

}

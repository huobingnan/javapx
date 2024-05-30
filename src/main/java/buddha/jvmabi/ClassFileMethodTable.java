package buddha.jvmabi;

import buddha.jvmabi.method.MethodItem;

import java.util.ArrayList;
import java.util.List;

public final class ClassFileMethodTable {
    private final List<MethodItem> methods;
    private final int length;

    public ClassFileMethodTable(int count) {
        methods = new ArrayList<>(count);
        this.length = count;
    }

    public int length() { return length; }

    public void append(MethodItem item) { methods.add(item); }

    public MethodItem get(int idx) { return methods.get(idx); }

}

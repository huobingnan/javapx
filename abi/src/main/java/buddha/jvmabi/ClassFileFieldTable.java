package buddha.jvmabi;

import buddha.jvmabi.field.FieldItem;


public class ClassFileFieldTable {
    private final FieldItem[] fields;
    private int appendCursor;

    public ClassFileFieldTable(int count) {
        fields = new FieldItem[count];
        appendCursor = 0;
    }

    public int length() { return fields.length; }

    public void append(FieldItem item) { fields[appendCursor++] = item; }

    public FieldItem get(int idx) { return fields[idx]; }

}

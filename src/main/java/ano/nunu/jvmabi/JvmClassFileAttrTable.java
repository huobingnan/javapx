package ano.nunu.jvmabi;

import ano.nunu.jvmabi.attribute.IJvmAttribute;

import java.io.Serializable;

public final class JvmClassFileAttrTable implements Serializable {
    private IJvmAttribute[] attributes;
    private int appendCursor = 0;

    public JvmClassFileAttrTable(int length) { attributes = new IJvmAttribute[length]; }

    public IJvmAttribute get(int idx) { return attributes[idx]; }

    @SuppressWarnings({ "unchecked" })
    public <T> T getExact(int idx) { return (T)attributes[idx]; }

    public void append(IJvmAttribute attr) { attributes[appendCursor++] = attr; }

    public boolean isFull() { return appendCursor == attributes.length; }

    public int length() { return attributes.length; }
}


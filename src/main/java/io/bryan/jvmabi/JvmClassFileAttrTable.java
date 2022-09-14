package io.bryan.jvmabi;

import io.bryan.jvmabi.attribute.IAttribute;

import java.io.Serializable;

public final class JvmClassFileAttrTable implements Serializable {
    private IAttribute[] attributes;
    private int appendCursor = 0;

    public JvmClassFileAttrTable(int length) { attributes = new IAttribute[length]; }

    public IAttribute get(int idx) { return attributes[idx]; }

    @SuppressWarnings({ "unchecked" })
    public <T> T getExact(int idx) { return (T)attributes[idx]; }

    public void append(IAttribute attr) { attributes[appendCursor++] = attr; }

    public boolean isFull() { return appendCursor == attributes.length; }
}


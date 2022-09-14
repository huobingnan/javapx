package io.bryan.jvmabi;

import io.bryan.jvmabi.constant.IJvmConstant;

public final class JvmClassFileConstantPool {
    private final IJvmConstant[] pool;
    private int appendCursor = 1;

    public JvmClassFileConstantPool(int constantPoolCount) {
        pool = new IJvmConstant[constantPoolCount];
    }

    public void append(IJvmConstant constant) { pool[appendCursor++] = constant; }

    public int getConstantPoolCount() { return pool.length; }

    public IJvmConstant get(int index) { return pool[index]; }

    @SuppressWarnings({ "unchecked" })
    public <T> T getExact(int index) { return (T) pool[index]; }
}

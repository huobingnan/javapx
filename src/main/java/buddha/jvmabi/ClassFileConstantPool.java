package buddha.jvmabi;

import buddha.jvmabi.constant.IJvmConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 字节码文件的常量池区域
 */
public final class ClassFileConstantPool {
    private final List<IJvmConstant> pool;
    private final int length;

    public ClassFileConstantPool(int constantPoolCount) {
        pool = new ArrayList<>(constantPoolCount);
        length = constantPoolCount;
        pool.add(null);
    }

    public void append(IJvmConstant constant) { pool.add(constant); }

    public int length() { return length; }

    public IJvmConstant get(int index) { return pool.get(index); }

    @SuppressWarnings({ "unchecked" })
    public <T extends IJvmConstant> T getExact(int index) { return (T) pool.get(index); }

    /**
     * 找到常量池中给定tag值的所有常量
     * @param tag 常量tag
     * @return 常量列表
     * @see ClassFileConstantTagConst
     * @param <T> 常量类型
     */
    @SuppressWarnings({"unchecked"})
    public <T extends IJvmConstant> List<T> findByTag(int tag) {
        final List<T> result = new ArrayList<>();
        for (IJvmConstant constant : pool) {
            if (constant.getTag() == tag) {
                result.add((T) constant);
            }
        }
        return result;
    }
}

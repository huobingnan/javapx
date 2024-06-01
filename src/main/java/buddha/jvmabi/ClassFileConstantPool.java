package buddha.jvmabi;

import buddha.jvmabi.constant.IJvmConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * 字节码文件的常量池区域
 */
public final class ClassFileConstantPool {
    /**
     * 常量池中常量的列表
     */
    private final List<IJvmConstant> pool;
    /**
     * length代表常量池的大小，它在常量池被创建的时候大小就已经确定了
     * 并在之后的使用中不会发生变化
     */
    private final int length;

    public ClassFileConstantPool(int constantPoolCount) {
        pool = new ArrayList<>(constantPoolCount);
        length = constantPoolCount;
        pool.add(null);
    }

    /**
     * 向常量池中添加一个常量条目
     * @param constant 常量条目
     */
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

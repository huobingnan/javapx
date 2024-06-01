package buddha.jvmabi.constant;

import buddha.jvmabi.reader.IByteCodeReader;

/**
 * JVM Constant常量接口
 * @author BRYAN
 */
public interface IJvmConstant {
    /**
     * 获取常量标签
     * @see buddha.jvmabi.ClassFileConstantTagConst
     * @return 常量标签
     */
    byte getTag();

    /**
     * 通过IByteCodeReader读取常量池信息
     * @see IByteCodeReader
     * @param reader IByteCodeReader
     */
    void read(IByteCodeReader reader);
}

package buddha.javapx.abi.constant;

import buddha.javapx.abi.ClassFileConstantTagConst;
import buddha.javapx.abi.annotation.U1;
import buddha.javapx.abi.reader.IByteCodeReader;

/**
 * JVM Constant常量接口
 * @author BRYAN
 */
public interface IJvmConstant {
    /**
     * 获取常量标签
     * @see ClassFileConstantTagConst
     * @return 常量标签
     */
    @U1 int getTag();

    /**
     * 通过IByteCodeReader读取常量池信息
     * @see IByteCodeReader
     * @param reader IByteCodeReader
     */
    void read(IByteCodeReader reader);
}

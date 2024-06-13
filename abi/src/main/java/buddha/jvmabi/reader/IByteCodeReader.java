package buddha.jvmabi.reader;

import buddha.jvmabi.annotation.U1;
import buddha.jvmabi.annotation.U2;
import buddha.jvmabi.annotation.U4;
import buddha.jvmabi.annotation.U8;

/**
 * 字节码读取接口
 * @author BRYAN
 */
public interface IByteCodeReader extends AutoCloseable {
    /**
     * 读取一个字节的无符号整数
     * @return U1
     */
    @U1 int readU1();

    /**
     * 读取两个字节的无符号整数
     * @return U2
     */
    @U2 int readU2();

    /**
     * 读取四个字节的无符号整数
     * @return U4
     */
    @U4 int readU4();

    /**
     * 读取八个字节的无符号整数
     * @return U8
     */
    @U8 long  readU8();
}

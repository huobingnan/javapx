package buddha.jvmabi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 一个字节的无符号整数
 * 这个注解仅仅作为提示方便开发者和使用者,<b>U2</b>, <b>U4</b> <b>U8</b>也是同样的
 * @see U2
 * @see U4
 * @see U8
 * @author BRYAN
 */
@Target({ElementType.TYPE_USE, ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface U1 {
}

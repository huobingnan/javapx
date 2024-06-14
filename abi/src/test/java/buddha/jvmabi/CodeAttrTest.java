package buddha.jvmabi;

import buddha.javapx.abi.AttributeType;
import buddha.javapx.abi.ClassFile;
import buddha.javapx.abi.attribute.CodeAttr;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CodeAttrTest extends BaseTest {

    @Test
    public void lookupCodeArray() {
        final ClassFile jvmClassFile = ClassFile.parse(reader);
        final List<CodeAttr> codeAttrs =
                jvmClassFile.getAttributeTable().findByType(AttributeType.CODE);
        final CodeAttr code = codeAttrs.get(0);
        final byte[] codeArray = code.getCode();
        for (int i = 0; i < codeArray.length; i++) {
            System.out.print(Integer.toHexString(codeArray[i] & 0xff));
            System.out.print(' ');
        }
    }

    @Override
    String getClassFilename() {
        return "data/BiLock.class";
    }
}

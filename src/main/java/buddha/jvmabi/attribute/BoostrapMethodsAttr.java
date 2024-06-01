package buddha.jvmabi.attribute;

import buddha.jvmabi.AttributeType;
import buddha.jvmabi.ClassFileConstantPool;
import buddha.jvmabi.reader.IByteCodeReader;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoostrapMethodsAttr implements IJvmAttribute {

    private int length;
    private BootstrapMethod[] methods;

    public BoostrapMethodsAttr(ClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public AttributeType type() { return AttributeType.BOOTSTRAP_METHODS; }

    @Override
    public void read(ClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        methods = new BootstrapMethod[reader.readU2()];
        for (int i = 0; i < methods.length; i++) {
            final int methodRef = reader.readU2();
            final int[] args = new int[reader.readU2()];
            for (int j = 0; j < args.length; j++) { args[i] = reader.readU2(); }
            methods[i] = new BootstrapMethod(methodRef, args);
        }
    }

    @Override
    public int getAttrLength() { return length; }
}

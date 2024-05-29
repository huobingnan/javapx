package buddha.jvmabi.attribute;

import buddha.jvmabi.JvmClassFileConstantPool;
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

    public BoostrapMethodsAttr(JvmClassFileConstantPool pool, IByteCodeReader reader) { read(pool, reader); }

    @Override
    public String name() { return "BootstrapMethods"; }

    @Override
    public void read(JvmClassFileConstantPool pool, IByteCodeReader reader) {
        length = reader.readU4();
        methods = new BootstrapMethod[reader.readU2()];
        for (int i = 0; i < methods.length; i++) {
            final short methodRef = reader.readU2();
            final short[] args = new short[reader.readU2()];
            for (int j = 0; j < args.length; j++) { args[i] = reader.readU2(); }
            methods[i] = new BootstrapMethod(methodRef, args);
        }
    }

    @Override
    public int getAttrLength() { return length; }
}

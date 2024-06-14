package buddha.javapx.abi;

import buddha.javapx.abi.attribute.IJvmAttribute;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class ClassFileAttrTable implements Serializable {
    private final List<IJvmAttribute> attributes;
    private final int length;
    public ClassFileAttrTable(int length) {
        attributes = new ArrayList<>(length);
        this.length = length;

    }

    public IJvmAttribute get(int idx) { return attributes.get(idx); }

    @SuppressWarnings({ "unchecked" })
    public <T extends IJvmAttribute> T getExact(int idx) { return (T)attributes.get(idx); }

    public void append(IJvmAttribute attr) { attributes.add(attr); }
    public int length() { return length; }

    @SuppressWarnings("unchecked")
    public <T extends IJvmAttribute> List<T> findByType(AttributeType type) {
        final List<T> result = new ArrayList<>();
        for (IJvmAttribute attr : attributes) {
            if (attr.type() == type
                    && attr.type().getAttrClass().isAssignableFrom(attr.getClass())) {
                result.add((T)attr);
            }
        }
        return result;
    }
}


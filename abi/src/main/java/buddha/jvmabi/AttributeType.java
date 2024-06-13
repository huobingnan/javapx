package buddha.jvmabi;


import buddha.jvmabi.attribute.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttributeType {

    CODE("Code", CodeAttr.class),
    LINE_NUMBER_TABLE("LineNumberTable", LineNumberTableAttr.class),
    EXCEPTIONS("Exceptions", ExceptionsAttr.class),
    LOCAL_VARIABLE_TABLE("LocalVariableTable", LocalVariableTableAttr.class),
    SOURCE_FILE("SourceFile", SourceFileAttr.class),
    CONSTANT_VALUE("ConstantValue", ConstantValueAttr.class),
    DEPRECATED("Deprecated", DeprecatedAttr.class),
    SYNTHETIC("Synthetic", SyntheticAttr.class),
    INNER_CLASSES("InnerClasses", InnerClassesAttr.class),
    BOOTSTRAP_METHODS("BootstrapMethods", BootstrapMethodsAttr.class),
    METHOD_PARAMETERS("MethodParameters", MethodParametersAttr.class),
    STACK_MAP_TABLE("StackMapTable", StackMapTableAttr.class),
    ;

    private final String name;
    private final Class<? extends IJvmAttribute> attrClass;

}


# jvmabi introduction
jvmabi： Java Virtual Machine Application Binary Interface，是一个使用Java完成的解析JVM字节码文件的框架。
该框架的愿景为：
1. 从零到一实现一个字节码解析类库，从中了解JVM的一些规范，和Java代码编译后的组织方式。
2. 借助GraalVM的native-image工具，实现一个输出友好且漂亮的字节码解析工具(CLI)。

## Requirement
1. JDK 11+
2. Gradle 8.x
3. GraalVM native-image

## 资料参考
1. 《深入理解Java虚拟机》第三版
2. 《深入理解JVM字节码》
3. 《GraalVM与Java静态编译原理与应用》
4.  [JVM Specification](https://docs.oracle.com/javase/specs/jvms/se8/html/)


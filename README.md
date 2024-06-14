# javapx介绍
javapx是对JDK javap工具功能的一种增强。对比于JDK的javap反编译工具，javapx在其基本
功能的基础之上，加入了漂亮的TUI（Terminal GUI），让开发者在开发过程中更加愉悦。

项目的愿景：
1. 从零到一实现一个字节码解析类库，从中了解JVM的一些规范，和Java代码编译后的组织方式。
2. 借助GraalVM的native-image工具，实现一个输出友好且漂亮的字节码解析工具(CLI)。

## 开始项目你需要具备的
1. JDK 11+
2. Gradle 8.x
3. GraalVM native-image

## 模块结构
1. **abi**模块：完成基本的class文件解析功能
2. **tui**模块：实现javapx命令行使用接口和TUI界面

## 资料参考
1. 《深入理解Java虚拟机》第三版
2. 《深入理解JVM字节码》
3. 《GraalVM与Java静态编译原理与应用》
4.  [JVM Specification](https://docs.oracle.com/javase/specs/jvms/se8/html/)


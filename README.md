# common-utils

### 使用

在您的 Maven POM 文件里加入：

```
<dependency>
  <groupId>com.github.sigalhu</groupId>
  <artifactId>common-utils</artifactId>
  <version>1.0.0</version>
</dependency>
```

### 版本升级

**1.0.0 新增工具类、Pipeline**

* 新增 AnnotationReflectUtils 工具类，功能包括：
    * 修改注解的属性
* 新增 Assert 工具类，功能包括：
    * 可以通过实例化或在静态方法传入 function 来指定要抛出的异常
    * 判断 expression、object、text、array、collection、map 的状态以确定是否抛出异常
* 新增 MetricCalculator 工具类，功能包括：
    * 计算 percent、tps、latency、incr、sum
* 新增 ThreadPoolMonitor 工具类，功能包括：
    * 打印传入线程池的状态日志
* 新增 Try 工具类，功能包括：
    * 捕获声明 throws 的方法抛出的异常，并抛出 RuntimeException 或执行兜底方法

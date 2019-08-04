# common-utils

### 使用

在您的 Maven POM 文件里加入：

```
<dependency>
  <groupId>com.github.sigalhu</groupId>
  <artifactId>common-utils</artifactId>
  <version>1.0.2</version>
</dependency>
```

### 版本升级

**future**

* 新增 MathUtils，功能包括：
    * 比较数据类型的数值大小
    * 判断是否相等，当为数据类型时，比较数值是否相等
* 升级 BeanUtils，功能包括：
    * 获取 bean 指定字段路径值的集合
    * 缓存所有获取的 getter/setter

**1.0.2**

* 新增 BeanUtils，功能包括：
    * 获取 bean 的所有属性 getter Function，性能接近直接调用 bean getter
    * 获取 bean 的所有属性 setter BiConsumer，性能接近直接调用 bean setter

**1.0.1**

* 新增 IdGenerator 以生成 id
    * RandomIdGenerator 为随机 id 生成器，同一进程内生成的 id 唯一
    * UniqueIdGenerator 为唯一 id 生成器，实例数不超过64时，生成的 id 唯一

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
* 新增 Processors 以支持流水线处理
    * Processor 为基础单元，可以执行处理逻辑，跳过处理或打印耗时
    * PipelineProcessor 为流水线，可以依次处理传入的 Processors，当传入的 Processor 发生异常或返回成功/失败时，中止执行
    * DispatchProcessor 为分发器，可以依次处理传入的 Processors，每个 Processor 互不影响，都会被执行
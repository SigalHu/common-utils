# common-utils

### 使用

在您的 Maven POM 文件里加入：

```
<dependency>
  <groupId>com.github.sigalhu</groupId>
  <artifactId>common-utils</artifactId>
  <version>1.0.6</version>
</dependency>
```

### 版本升级

**feature**

* 升级 CostTimer，功能包括：
    * 支持添加耗时信息
    * 支持累次计时
    * 提升计时精度
* 新增 JvmUtils，功能包括：
    * 支持获取 GC 总次数
    * 支持主动触发 GC
* 新增最近均值计算器，功能包括：
    * 计算并获取最近窗口的均值
    * 比较当前值与最近均值

**1.0.6 数组工具和 JSON 的左值相等**

* 新增 ArrayUtils，功能包括：
    * 将包括基本数据类型在内的数组对象转化为 Object 数组
    * 将包括基本数据类型在内的数组对象转化为固定大小的列表
* 升级 BeanUtils，功能包括：
    * 获取 bean 指定字段路径值的集合，包括数组字段
* 升级 JsonUtils，功能包括：
    * 判断 JSON 对象左值和右值是否相等，只比较左值及嵌套的 JSON 对象包含的属性

**1.0.5 计时器**

* 新增 CostTimer，功能包括：
    * 提供阶段计时/闭包计时/Closeable计时
    * 支持子计时器，对子阶段进行计时
    * 支持参数控制子计时器是否计时，提高性能和灵活性
* 升级 Try 工具类，功能包括：
    * 捕获声明 throws 的 Runnable 抛出的异常，并抛出 RuntimeException 或执行兜底方法

**1.0.3 过滤 Bean、可以比较不同类型数值的 NumberUtils**

* 新增 BeanFilter，功能包括：
    * 判断传入实例是否符合筛选条件
    * 关系运算符包括等于、包含、小于、小于等于、大于、大于等于、属于
    * 逻辑运算符包括与、或、非
* 新增 NumberUtils，功能包括：
    * 比较数据类型的数值大小
    * 判断是否相等，当为数据类型时，比较数值是否相等
    * 将数据类型的数值转换为 64 bits
* 升级 BeanUtils，功能包括：
    * 获取 bean 指定字段路径值的集合
    * 缓存所有获取的 getter/setter

**1.0.2 高效的 BeanUtils**

* 新增 BeanUtils，功能包括：
    * 获取 bean 的所有属性 getter Function，性能接近直接调用 bean getter
    * 获取 bean 的所有属性 setter BiConsumer，性能接近直接调用 bean setter

**1.0.1 id 生成器**

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
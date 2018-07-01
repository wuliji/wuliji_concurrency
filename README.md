# wuliji_concurrency
高并发知识及场景实战

一。

总体架构：SpringBoot，Maven，JDK8，MSQL

基础组件：Mybits，Guava，Lombok，Redis，Kafka

高级组件：Joda-Time，Atomic包，JUC，AQS，ThreadLocal，RateLimiter，ThreadPool，curator等等

二。

CPU cache：时间局部性与空间局部性

CPU缓存一致性（MESI）:保证多个CPU cache之间缓存共享数据的一致

CPU乱序执行优化：CPU会优化相关代码的执行顺序。

Java内存模型：read，load，use，assign，store，write，lock，unlock

三。

原子性：提供了互斥访问，同一时刻只能有一个线程对它进行操作

LongAdder类优缺点：JVM允许将64位的数据拆分成两个32位数据处理。将热点数据分散计数，最后将各个数组的值进行相加操作，达到计数效果。但是在统计的过程中可能会引起小型误差。

可见性：一个形成对主内存的修改可以及时的被其他线程看见

有序性：一个形成观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序。

synchronized修饰方法时只是同步该方法，而不会对其他非同步修饰的方法产生作用。

synchronized方法作用效果不会继承到子类当中，子类如果想用包含父类的同步方法则也要加上Synchronized。

Synchronized修饰全局类时可以有两种。一是修饰static方法，二是在代码块中使用.class修饰

volatile场景用法：使用为状态标记量，比如某个线程的结束标记。

四。

安全发布对象问题：一是类属性可修改。而是类初始化未完成导致this逸出。

单例模式：饿汉式与懒汉式（双重检索 volatile）与枚举类型。

五。

不可变对象：对象所有域都是final类型。对象创建时没有发生this逃逸。创建过后状态不能被修改。可以是final修饰的（对象仍是不安全的），可以是Collections.unmodifiableXXX的集合对象，可以是ImmutableXXX的集合对象。

线程封闭：局部变量无并发的相关问题又叫堆栈封闭。用ThreadLocal定义相关类。

线程不安全类：StringBuilder，SimpleDateFormat，ArrayList，HashSet集合等。

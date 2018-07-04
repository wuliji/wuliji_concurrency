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

同步容器：Vector，Stack，HashTable，Collections中的同步类集合。在线程安全的集合中，在foreach循环和迭代器iterator中做remove操作会出现ConcurrentModification异常

并发容器：CopyOnWriteArrayList。读写分离，在写的时候会复制一个副本防止其他线程读操作的数据不一致。读操作不加锁，写操作加锁。CopyOnWriteArraySet底层为CopyOnWriteArrayList，ConcurrentSkipListSet和TreeSet一样支持自然排序，键是有序的，对于增加操作为线程安全的，对于remove操作没有加同步，需要自行同步。ConcurrentSkipListMap为TreeMap的高并发容器，基于跳表实现。

六。

AQS：使用Node实现FIFO队列，可以用于构建锁或者其他同步装置的基础框架。利用了int类型state表示状态，使用方法继承。通过acquire和release的方法操纵状态。可以同时实现排他锁和共享锁模式。

CountDownLatch：闭锁 可以让一个线程等待某一个状态

Semaphore：信号量 提供有限使用的资源场景

CyclicBarrier：栅栏 多个线程之间相互等待，一组线程达到某个状态时才会执行。

ReentrantLock：核心为Lock和unLock。可重入锁，即线程进入一次会对锁状态标记加一。释放减一直到释放锁。相比Synchronized他1.可以指定是否是公平锁。2.提供了一个Condition类，可以分组唤醒需要唤醒的线程。3.提供了能够中断等待锁的线程的机制，lock.lockInterruptibly().

FutureTask：用线程计算返回值，实现了Callable与FutureTask接口直接调用call方法进行封装，然后调用get获取结果。
Fork/Join框架：将计算任务拆分成若干个子任务，然后进行递归计算处理，最后返回最终的结果。

ArrayBlockingQueue：初始化时需要指定大小。遵从FIFO

DelayQueue：多用于定时管理对象连接，内部对象维持着一个延迟时间，只有当延迟时间到了才回进行出队。

LinkedBlockingQueue：可指定初始化大小，或者不指定默认Integer最大值作为容量。遵从FIFO 

PriorityBlockingQueue：实现了Comparator接口，进行内部排序。

SynchronousQueue：只允许一个元素进入，直到被消费，认为是无界队列，内部没有容量。

七。

线程池：重用存在的线程，减少对象创建，消亡的开销，性能好。可有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多的资源竞争，避免阻塞。并且可以提供定时执行，定期执行，单线程，并发数控制等功能。


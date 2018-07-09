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

newCachedThreadPool：缓存线程池，可以拓展大小。

newFixedThreadPool：固定大小的线程池。

newSingleThreadExecutor：单一线程大小的线程池。

newScheduledThreadPool：可做定时任务的线程池。与Timer类有异曲同工之处。

八。

使用本地变量。

使用不可变的类。

最小化锁的范围区域。

尽量使用线程池来启动线程。

尽量使用同步也不要使用线程的wait与notify。

使用阻塞队列实现生产者与消费者模式。

使用并发集合而不是加了锁的同步集合。

使用Semaphore创建有界的访问。

宁可使用同步的代码块也不使用同步的方法。

避免使用静态变量。

九。

高并发扩容处理：

1.垂直扩容：提高系统部件能力，采用技术手段。

2.水平扩容：增加更多系统成员来实现，采用硬件手段。

读操作拓展：memecache，redis，CDN技术等。

写操作拓展：Cassandra，Hbase等。

十。

缓存特征：命中率，最大元素，清空策略（FIFO，LFU，LRU）过期时间等。

业务场景：读多写少的场景，缓存的过期策略与更新。

本地缓存：编程实现比如Guava Cache

分布式缓存：Memcache与Redis

GuavaCache：采用ConcurrentHashMap思想，分段的形式进行缓存的存取，并且键值用的weakReference引用，保证在下一次的垃圾回收中进行垃圾回收。

Memecache：客户端路由请求到相应的服务端上面去，采用的是一致性哈希算法。slabc_lass包含slab，slab又包含page，page又包含真正存放数据的chunk中。每次数据要进行存取时，memcache总能找到与它空间最相近的chunk，但是避免不了存储空间的浪费。LRU算法只针对slab。key最大为250个字节，单个的item最大空间为1M，memcached的服务器端是不安全的，可被外界修改。

Redis：基于内存的单线程非关系型数据库系统。底层采用redis核心对象来存储。支持数据的持久化AOF与RDB。支持主从数据备份。具有原子性操作。通过JedisPool来实现redis相关操作

高并发场景常见缓存问题

1.缓存一致性：缓存过期策略与缓存更新策略方面解决。

2.缓存并发问题：锁的机制，数据库显式锁的设置。

3.缓存穿透问题：对查询结果为空的对象进行缓存，通过设置对象属性。对所有为空的数据进行单独存放，并在请求前做拦截处理。

4.缓存的雪崩现象：即大量请求直接到达数据库导致系统崩溃。可以通过一致性哈希算法解决。包括限流，降级，多级缓存等。

十一。

消息队列：只做消息的分发，先发送先处理，节点的动态增删和消息的持久化。吞吐量提升，系统内部效率提高。使用消息队列的原因是生产和消费的速度或稳定性等因素不一致。

优势：可以实现业务的解耦，削峰，最终一致性，流控，广播等。如果需要强一致性，关注业务的处理结果则使用RPC相关的调度更合适。通上游通知消息更新，下游系统进行相应业务的处理。

强一致性：分布式事务，难度大，成本高。

最终一致性：记录与补偿的方式。有个中间不确定过程，依靠定时任务进行更新。

Kafka：是Apache旗下的一款跨语言，高性能，分布式发布订阅消息队列。可以进行快速持久化。自动实现分布式与负载均衡。

Kafka中比较重要的是broker，producer通过发送消息到topic中的partition，partition接收到消息后将其持久化到硬盘，保留消息指定时长，consumer取出partition中的消息，并设置partition中的相应offset的值。

RabbitMQ：消息发布者将消息发送给相应的Exchange，Exchange将消息发送到队列queue中，其中Exchange有很多种，包含单播与广播。

十二。

对于一个项目，可以拆分为相应的子系统中。通过系统间的调用业务接口来实现系统之间的通信（Dubbo）。会增加管理上的复杂性，会带来服务器上的压力，增加了网络的开销。

业务优先考虑。

循序渐进，拆分与测试同步。

应用之间的通信：RPC（Dubbo）等，消息队列。

应用限流：限制总并发数，限制瞬时并发数。

计数器法：在一定时间内设置访问次数限制。

滑动窗口：根据时间推动进行数据传送。

漏桶算法：让接口以恒定的速率解决请求。

令牌桶算法：周期性的生产令牌，处理请求时需从桶中获得令牌再进行处理，如果令牌数不够则默认不处理。

服务降级：请求处理出错，返回默认的错误响应。包含超时，失败次数，故障，限流降级等。

服务熔断：软件系统中因某种原因使服务出现过载的现象，为了防止整个系统崩溃而采取的安全措施。

数据库分库与分表：

1.单个库的数据量太大。

2.单个数据库服务器压力过大。

3.单个表的数据量太大。

横向分表：表结构相同，根据数据量规模划分，一般对主键字段取余实现。

纵向分表：根据数据列属性进行分表操作。

高可用系统：任务调度系统分布式（elastic-job + zookeeper）

主备切换：apache curator + zookeeper 分布式锁。两台服务器共争一把锁，拿到锁的服务器对外提供服务，没有拿到的一直尝试去拿锁。


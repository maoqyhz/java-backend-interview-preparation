# Java 后端面试

> 这张是自己画的一个Java后端技术栈的思维导图，想去大厂的基本都要会。

![Java backend](.\images\Java backend.png)

[TOC]


##  Java 基础题

### 语言基础
1. **[什么是不可变类？Java如何实现不可变类？String为什么是不可变的？](https://www.cnblogs.com/jaylon/p/5721571.html)**
2. **[深拷贝和浅拷贝。如何实现深拷贝？](https://blog.csdn.net/pony_maggie/article/details/52091588)**
3. **[重写了`equals()`方法后需要同时重写`hashcode()`吗？](https://blog.csdn.net/u013679744/article/details/57074669/)**
    https://blog.csdn.net/zzg1229059735/article/details/51498310
4. **[字符串常量池](https://segmentfault.com/a/1190000009888357)**
5. **String、StringBuilder、StringBuffer**
6. **[final、finally和finalize的区别](https://blog.csdn.net/cyl101816/article/details/67640843)**
7. **[简单介绍一下java的反射机制？反射在哪些地方有应用场景？](https://www.sczyh30.com/posts/Java/java-reflection-1/#)**
8. Java8中接口可以添加静态方法和默认实现（default关键字）
9. **[动态代理一般有哪几种实现方式？动态代理的应用场景有哪些？](http://www.cnblogs.com/xiaoluo501395377/p/3383130.html)**
    http://www.importnew.com/26116.html 日志、计时、aop、自定义第三方操作等

### 泛型集合

1. **[HashMap是如何实现的？与HashTable的区别是什么？](https://blog.csdn.net/qq_27093465/article/details/52207135)**

### 多线程
1. **[并发特性 - 原子性、有序性、可见性](https://www.cnblogs.com/dolphin0520/p/3920373.html)**
     > 原子性：即一个操作或者多个操作 要么全部执行并且执行的过程不会被任何因素打断，要么就都不执行。 
     > 可见性：指当多个线程访问同一个变量时，一个线程修改了这个变量的值，其他线程能够立即看得到修改的值。 
     > 有序性：即程序执行的顺序按照代码的先后顺序执行，不进行指令重排列。

2. **synchronized实现原理?** 
      > `synchronized` 可以保证方法或者代码块在运行时，同一时刻只有一个进程可以访问，同时它还可以保证共享变量的内存可见性。 
      >
      > Java 中每一个对象都可以作为锁，这是 `synchronized` 实现同步的基础：
      >
      > 1. 普通同步方法，锁是当前实例对象
      > 2. 静态同步方法，锁是当前类的 class 对象
      > 3. 同步方法块，锁是括号里面的对象
      >
      > - **同步代码块**：`monitorenter` 指令插入到同步代码块的开始位置，`monitorexit` 指令插入到同步代码块的结束位置，JVM 需要保证每一个 `monitorenter` 都有一个 `monitorexit` 与之相对应。任何对象都有一个 Monitor 与之相关联，当且一个 Monitor 被持有之后，他将处于锁定状态。线程执行到 `monitorenter`指令时，将会尝试获取对象所对应的 Monitor 所有权，即尝试获取对象的锁。
      > - **同步方法**：`synchronized` 方法则会被翻译成普通的方法调用和返回指令如：`invokevirtual`、`areturn`指令，在 VM 字节码层面并没有任何特别的指令来实现被`synchronized` 修饰的方法，而是在 Class 文件的方法表中将该方法的 `access_flags` 字段中的 `synchronized` 标志位置设置为 1，表示该方法是同步方法，并使用**调用该方法的对象**或**该方法所属的 Class 在 JVM 的内部对象表示 Klass** 作为锁对象。
      > `synchronized` 是重量级锁，在JDK1.6中进行优化，如自旋锁、适应性自旋锁、锁消除、锁粗化、偏向锁、轻量级锁等技术来减少锁操作的开销。 

3. **volatile的实现原理？**
     > volatile是轻量级的锁，**它不会引起线程上下文的切换和调度**。
     >
     > 1. `volatile` 可见性：对一个 `volatile` 的读，总可以看到对这个变量最终的写。
     > 2. `volatile` 原子性：`volatile` 对单个读 / 写具有原子性（32 位 Long、Double），但是复合操作除外，例如 `i++` 。
     > 3. JVM 底层采用“内存屏障”来实现 `volatile` 语义，**防止指令重排序**。

4. **Java内存模型（JMM）**
     > ![](.\images\15193024_vxfh.jpg)
     >
     > 
     >
     > ![](.\images\jvm_memory_thread.png)

5. HashMap是不是线程安全?如何体现？如何变得安全？

6. HashMap 和ConcurrentHashMap 的区别？

7. ConcurrentHashMap 的实现方式？

8. volatile关键字的作用？为什么使用AtomicLong而不使用Long?AtomicLong的底层是怎么实现的？ 

9. 实现线程安全的方式有哪些?并介绍一下实现? 

10. 线程池

11. CAS是一种什么样的同步机制？ 

12. CountDownLatch和CyclicBarrier的区别？ 

13. 线程加锁有哪些方式？synchronized和lock的区别？ 

14. 怎么控制线程，尽可能减少上下文切换？ 

15. 什么是乐观锁和悲观锁？

### I/O 


### JVM


## Java Web

### web基础

### Spring

## 数据库

## 设计模式

**创建型模式：**

- **单例模式**
- 抽象工厂模式
- 建造者模式
- 工厂方法模式
- 原型模式

**结构型模式：**

- 适配器模式
- 桥接模式
- **装饰模式**
- 组合模式
- 外观模式
- 享元模式
- **代理模式**

**行为型模式：**

- 模版方法模式
- 命令模式
- 迭代器模式
- **观察者模式**
- 中介者模式
- 备忘录模式
- 解释器模式
- 状态模式
- 策略模式
- 职责链模式
- 访问者模式。

### 代理模式
![proxy](.\images\proxy.png)

**应用：** Spring AOP主要实现方式
可看做是对源程序加壳处理。代理模式可分为静态代理和动态代理。

- 静态代理需要实现原代理的接口，不利于扩展。
-  动态代理不需要实现接口，更通用。动态代理分为 JDK 代理和cglib代理。前者由 JDK 实现，但是需要实现接口，无法代理类。后者是一个单独的 Java 库，既可以代理接口也可以代理类。

### 单例模式
保证类的实例只有一个。
**应用：**例如计数器、Spring默认创建Bean等。需要保证一定的上下文状态，否则可以使用静态类。
懒汉（第一次获取时进行实例化，线程不安全）和饿汉单例（类加载时就实例化，线程安全）。
**线程不安全：**在并发环境下，判断失效，产生多个实例。
**如何改造懒汉单例为线程安全？**
1. 双重检查锁定
```java
public static Singleton getInstance(){
	//先检查实例是否存在，如果不存在才进入下面的同步块
	if(instance == null){
		//同步块，线程安全的创建实例
		synchronized (Singleton.class) {
			//再次检查实例是否存在，如果不存在才真正的创建实例
			if(instance == null){
				instance = new Singleton();
			}
		}
	}
	return instance;
}
```
2. 使用静态内部类实现
```java
/**
 * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
 * 没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。
 */
private static class SingletonHolder{
	// 静态初始化器，由JVM来保证线程安全
	private static Singleton instance = new Singleton();
}

public static Singleton getInstance(){
	return SingletonHolder.instance;
}
```
3. 枚举实现
```
public enum Singleton {
    /**
     * 定义一个枚举的元素，它就代表了Singleton的一个实例。
     */
    uniqueInstance;
    /**
     * 单例可以有自己的操作
     */
    public void singletonOperation(){
        //功能处理
    }
}
```

###  建造者模式


![builder](D:\Coding\git repo\java-backend-interview-preparation\images\builder.png)
**思想：**对象和对象创建过程分离解耦。由于抽象的Builder类的存在，使得创建人不需要清楚知道待创建对象的细节，只需要关注创建的过程。通过Director来隔离客户和生产过程。通过Director类就能够完全创建出一个复杂对象。

### 工厂三兄弟
**简单工厂**
![simplefactory](.\images\simplefactory.png)

**工厂方法**
![factory2](.\images\factory2.png)

**抽象工厂**
![factory3](.\images\factory3.png)


简单工厂：一个工厂负责创建多个抽象的实例。
工厂方法：每个类的实例都对应一个工厂类来创建相应的对象。
抽象工厂：针对于更多类的产品。一个工厂负责一类产品的创建。

### 原型模式
![yuanxing](.\images\yuanxing.png)
主要是用于对象的复制。一般直接实现`Cloneable`的`clone()`方法即可。


## 算法与数据结构

1. 逆序输出的可以使用`Stack<T>`。
2. Java中尽可能不用递归，防止出现栈溢出。必要时可以使用尾递归（但Java中为进行优化）
3. hash表
4. 数组和链表
5. 栈和队列
6. 排序算法 





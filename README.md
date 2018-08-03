# Java 后端面试

> 这张是自己画的一个Java后端技术栈的思维导图，想去大厂的基本都要会。

![Java backend](.\images\Java backend.png)


## 面试题

### Java 基础题
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
10. **[HashMap是如何实现的？与HashTable的区别是什么？](https://blog.csdn.net/qq_27093465/article/details/52207135)**

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

### 建造者模式
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

## 多线程
1. [原子性、有序性、可见性](https://www.cnblogs.com/dolphin0520/p/3920373.html)
2. Java内存模型（JMM）



## 算法题

1. 逆序输出的可以使用`Stack<T>`。
2. Java中尽可能不用递归，防止出现栈溢出。必要时可以使用尾递归（但Java中为进行优化）
3. hash表





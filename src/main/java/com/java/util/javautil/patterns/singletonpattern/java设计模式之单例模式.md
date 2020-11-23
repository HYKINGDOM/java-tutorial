### Java设计模式之单例模式

单例模式是指在项目运行中，一个类不论有多少对他的调用者，他只会存在一个实例

### 例1，经典单例模式：

```java
public class SingleTon {

    private static SingleTon singleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }
}

```



例子中类的构造函数私有，只能调用静态方法**getInstance( )**，调用的时候会判断当前类有没有被实例化，没有则创建，有则返回这个已经创建的实例。



但是经典单例模式也存在问题，当多线程操作的时候，由于多线程的实际是时间片的切换，所以当一个线程运行到if（singleTon == null）判断为true，准备进入下一行，进行实例化的时候，多线程切换到另一个线程，这时候到同样的位置，依然为true，继续实例化，然后当切回之前的线程依然会实例化，导致存在两个实例，单例模式也就失败了，对于这个问题进行优化。

### 例2 饿汉模式

```java
public class SingleTon {

    private static SingleTon singleTon = new SingleTon();

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }
}
```

饿汉模式就是在，调用**getInstance( )**方法之前就进行实例化，不管这个类有没有被调用JVM中都会存在该类的实例。

### 例3  懒汉模式

```java
public class SingleTon {

    private static SingleTon singleTon = null;

    private SingleTon() {
    }

    public synchronized static SingleTon getInstance() {
        if (singleTon == null) {
            singleTon = new SingleTon();
        }
        return singleTon;
    }
}
```

懒汉模式就是在，调用**getInstance( )**方法时候使用synchronized进行同步化，必须等到一个线程中的该方法执行完成后，再会被其他线程调用

饿汉模式在自己被加载时就将自己实例化，如果从资源利用效率角度来讲，比懒汉模式稍差些。但是从速度和反应时间以及性能的角度来讲，却要比懒汉模式要稍好些

### 例4 双重检查加锁

```java
public class SingleTon {
   	//volatile关键字是为了禁止编译器对 volatile关键字修饰的变量进行重排序，
    //并保证volatile变量的读操作发生在写操作之后
    private volatile static SingleTon singleTon = null;

    private SingleTon() {
    }

    public static SingleTon getInstance() {
        if (singleTon == null) {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    singleTon = new SingleTon();
                }
            }
        }
        return singleTon;
    }
}
```

这样的做法会对创建对象进行两次检查，其中一次检查加入了同步方法，而当第二次调用的时候不会再因为同步方法消耗性能，该方法也很好的解决了多线程的情况下单例模式消耗资源的问题。



### 例5 静态内部类

```java
public class SingleTon { //完成了懒汉式的延迟加载，同时static保证了线程安全。
 
    private  SingleTon(){
    }
 
    public static SingleTon getIntance(){
        return SingletonHolder.mIntance;
    }
 
    //私有的，初始化的时候，没有调用getIntance方法则不会加载
    private static class SingletonHolder{ 
        //static,final是jvm提供的同步机制，初始化后就无法修改了
        private static final SingleTon mIntance = new SingleTon();  
    }
 
}
```

这种写法是最为推崇的写法，利用static final关键字的同步机制，初始化后就无法修改保证了线程安全，使用holder的方式保证了延迟加载，不适用不会被加载


### 参考代码链接

Gitee: https://gitee.com/singlekingdom/JavaDesignPatterns.git
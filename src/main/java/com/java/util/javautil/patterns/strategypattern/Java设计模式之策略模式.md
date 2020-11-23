##  Java设计模式之策略模式

### 简介
在策略模式（Strategy Pattern）中，一个类的行为或其算法可以在运行时更改。这种类型的设计模式属于行为型模式。
在策略模式中，我们创建表示各种策略的对象和一个行为随着策略对象改变而改变的 context 对象。策略对象改变 context 对象的执行算法。


### 代码演示

参考<Head First设计模式>这本书中的demo,实现代码

* 该类是鸭子的基类,想象中所有的鸭子都会有飞行,叫声,游泳三种行为,接下来我们来实现三种类型的鸭子绿头鸭,红头鸭,以及石头刻的鸭子

```java
public abstract class BaseDuck {
    public BaseDuck() {
    }
    /**
     * 种类
     */
    public abstract void display();
    public void fly() {
        System.out.println("~~im fly~~");
    }
    public void quack() {
        System.out.println("~~gaga~~");
    }
    public void swim() {
        System.out.println("~~im swim~~");
    }
}

```

* 先来绿头鸭
```java

public class GreenHeadBaseDuck extends BaseDuck {
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
}
```

* 再来红头鸭
```java
public class RedHeadBaseDuck extends BaseDuck {
    @Override
    public void display() {
        System.out.println("**RedHead**");
    }

}
```
* 然后有一天,新的需求来了,现在需要一种用石头雕刻的鸭子,它不会游泳,不会叫,不会飞,如果按照之前的继承方法,石头鸭子居然会飞,会叫,会游泳了,神了奇了!!
```java
public class StoneBaseDuck extends BaseDuck {
    @Override
    public void display() {
        System.out.println("~~stone duck~~");
    }
}
```

很明显这样是有问题的,该怎么解决呢,这时候就会想到我们可以重写基类的方法啊,让他不会叫不会飞,也不会游泳.好了问题看似解决了.
然后产品经理告诉你,现在需要鸭子的另一个性质--羽毛,然后我们在基类中加入羽毛属性,然后发现石头鸭子没有羽毛,接着我们又要去重写石头鸭子中的羽毛方法.....
接着一个又一个新属性被加上来,每天在新增修改,重写中疲于奔命....

这就是,** 继承的操作中对超类的局部改动，会影响继承他的子类，产生的溢出效应 **,软件开发中我们常说的开闭原则,对扩展开放,对修改关闭,
在现代项目中面对新的需求不断的增加,就需要提高代码中的可扩展性,降低复杂度,

* 分析项目中变化和不变化的部分，提取变化的部分，抽象成接口+实现
* 鸭子的那些属性是会变化的，将其分析提取出来

好了基于上面的思考,我们将上面的代码进行拆分重构,
* 首先我们先新建飞行行为接口,后面新增任何飞行动作,实现飞行这个接口,完成飞行动作的扩展.
```java
public interface FlyBehavior {

    /**
     * 飞行行为
     */
    void fly();
}
```
* 飞行动作好的实现
```java
public class GoodFlyBehavior implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("--GoodFly--");
    }

}
```
* 飞行动作一般的实现
```java
public class BadFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("--BadFly--");
    }
}
```
* 不会飞的实现
```java
public class NoFlyBehavior implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("--NoFly--");
    }
}
```


* 叫声接口,新增的叫声都实现该接口进行扩展
```java
public interface QuackBehavior {
    /**
     * 鸭子叫声行为
     */
    void quack();
}
```
* 嘎嘎叫的声音
```java
public class GaGaQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("__GaGa__");
    }
}
```
* 咯咯的叫声
```java
public class GeGeQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("__GeGe__");
    }
}
```
* 不会叫
```java

public class NoQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("__No Quack__");
    }
}
```
* 游泳的动作接口,跟上面两个接口一样
```java
public interface SwimBehavior {
    void swim();
}
```
* 游泳技术非常好
```java
public class GoodSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("~~Good swim~~");
    }
}
```
* 游泳技术一般
```java
public class BadSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("~~bad swim~~");
    }
}
```
* 不会游泳
```java
public class NoSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("~~no swim~~");
    }
}
```

* BaseDuck基类,在基类中调用之前的接口,完成相应的行为
```java
public abstract class BaseDuck {
    /**
     * 飞行动作接口
     */
    FlyBehavior mFlyBehavior;
    /**
     * 鸭子叫声接口
     */
    QuackBehavior mQuackBehavior;
    /**
     * 游泳接口
     */
    SwimBehavior mSwimBehavior;
    
    public BaseDuck() {
    }
    /**
     * 种类
     */
    public abstract void display();
    
    public void fly() {
        mFlyBehavior.fly();
    }
    /**
     * 设置新的飞行行为
     * @param flyBehavior
     */
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        mFlyBehavior = flyBehavior;
    }
    public void quack() {
        mQuackBehavior.quack();
    }
    /**
     * 设置新的叫声属性
     * @param quackBehavior
     */
    public void setQuackBehavior(QuackBehavior quackBehavior) {
        mQuackBehavior = quackBehavior;
    }
    public void swim() {
        mSwimBehavior.swim();
    }
}
```
* 绿头鸭品种,绿头鸭中飞行行为一般,会嘎嘎叫,游泳非常好,通过绿头鸭的构造方法自定义属性
```java
public class GreenHeadBaseDuck extends BaseDuck {
    public GreenHeadBaseDuck(){
        mFlyBehavior = new BadFlyBehavior();
        mQuackBehavior = new GaGaQuackBehavior();
        mSwimBehavior = new GoodSwimBehavior();
    }
    @Override
    public void display() {
        System.out.println("**GreenHead**");
    }
}
```
* 红头鸭的品种
```java
public class RedHeadBaseDuck extends BaseDuck {
    public RedHeadBaseDuck() {
        mFlyBehavior = new GoodFlyBehavior();
        mQuackBehavior = new GeGeQuackBehavior();
        mSwimBehavior = new BadSwimBehavior();
    }
    @Override
    public void display() {
        System.out.println("**RedHead**");
    }
}
```
* 石头做的鸭子,实现自定义属性
```java
public class StoneBaseDuck extends BaseDuck {
    public StoneBaseDuck(){
        mFlyBehavior = new NoFlyBehavior();
        mQuackBehavior = new NoQuackBehavior();
        mSwimBehavior = new NoSwimBehavior();
    }
    @Override
    public void display() {
        System.out.println("**StoneBaseDuck**");
    }
}
```

* 测试方法
```java
public class StimulateDuck {

    public static void main(String[] args) {

        BaseDuck mGreenHeadBaseDuck = new GreenHeadBaseDuck();
        BaseDuck mRedHeadBaseDuck = new RedHeadBaseDuck();
        BaseDuck stoneBaseDuck = new StoneBaseDuck();

        mGreenHeadBaseDuck.display();
        mGreenHeadBaseDuck.fly();
        mGreenHeadBaseDuck.quack();
        mGreenHeadBaseDuck.swim();

        mRedHeadBaseDuck.display();
        mRedHeadBaseDuck.fly();
        mRedHeadBaseDuck.quack();
        mRedHeadBaseDuck.swim();
        System.out.println("==修改红头鸭属性==");
        mRedHeadBaseDuck.display();
        mRedHeadBaseDuck.setFlyBehavior(new NoFlyBehavior());
        mRedHeadBaseDuck.fly();
        mRedHeadBaseDuck.setQuackBehavior(new NoQuackBehavior());
        mRedHeadBaseDuck.quack();

        stoneBaseDuck.display();
        stoneBaseDuck.fly();
        stoneBaseDuck.quack();
    }

}

```
* 打印结果
```text
**GreenHead**
--BadFly--
__GaGa__
~~Good swim~~
**RedHead**
--GoodFly--
__GeGe__
~~bad swim~~
==修改红头鸭属性==
**RedHead**
--NoFly--
__No Quack__
**StoneBaseDuck**
--NoFly--
__No Quack__
```
这样一个策略模式的就完成了,所有的新增行为动作都不会修改原有的代码,只用在原来的基础上进行扩展,符合了开闭原则
### 总结
策略模式的实现就是分别对行为封装接口，实现算法族，基类里面放置行为接口对象，在子类里具体设定行为对象，
原则就是，分离变化部分，封装接口，基于接口编程各种功能，此模式使得行为算法的变化独立于算法使用者.

### 参考代码链接

Gitee: https://gitee.com/singlekingdom/JavaDesignPatterns.git
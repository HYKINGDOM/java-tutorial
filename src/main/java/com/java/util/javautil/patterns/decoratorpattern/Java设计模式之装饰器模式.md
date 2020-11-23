### Java设计模式之装饰器模式

**本文仅是个人观点，如有错误请指正**

#### 简介

装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。

这种模式创建了一个装饰类，用来包装原有的类，并在保持类方法签名完整性的前提下，提供了额外的功能。

我们通过下面的实例来演示装饰器模式的用法。其中，我们将把一个形状装饰上不同的颜色，同时又不改变形状类。

#### 代码实现

最近小王很烦恼，因为公司来了新的业务交给他，客户是一个咖啡馆，需要为他们设计一套点餐系统，他们具有商品咖啡，饮品，还有配料，这些各种组合起来就是各种咖啡品类，这些各种各样的组合下来小王一支找不到头绪，思前想后还是找到了公司里的大牛老李（咋又是老李），老李一听需求，脱口而出，这不就是装饰器模式么，小王听了一愣，啊，那应该怎么设计呢，老李说，咱们设计一抽象类里面放入单品的名字、计数、总价计算，然后分别设计咖啡基类、果汁类、调料类，然后各种咖啡果汁基于这些类进行扩展。。。。，小王听着云里雾里，老李一看说这个简单，我来说你来写代码。

- 首先我们设计咖啡馆的抽象类，我们给他加入品名，单价，计数变量然后设置get和set方法，再设计一个话费的抽象方法。

  ```java
  public abstract class BaseDrink {
  
      public String description;
  
      public Integer amount = 1;
  
      private float price = 0f;
  
  
      public void setDescription(String description) {
          this.description = description;
      }
  
      public String getDescription() {
          return description + "-" + this.getPrice();
      }
  
      public float getPrice() {
          return price;
      }
  
      public void setPrice(float price) {
          this.price = price * amount;
      }
  
      public void setAmount(Integer amount){
          if (amount >= 1){
              this.amount = amount;
          }
      }
  
  
  
      /**
       * 总价
       * @return
       */
      public abstract float cost();
  
  }
  ```

- 咖啡类让它继承咖啡馆的抽象类

  ```java
  public class Coffee extends BaseDrink {
  
      @Override
      public float cost() {
          return super.getPrice();
      }
  }
  ```

- 同样再加入果汁类

  ```java
  public class Juice extends BaseDrink {
      @Override
      public float cost() {
          return super.getPrice();
      }
  }
  ```

- 接着就是需要加入的调料类（装饰者），这里面我们加入他的构造函数，让它把需要加调料的饮品拿过来，加入调料再分别计算价格。

  ```java
  public class Decorator extends BaseDrink {
  
      private BaseDrink obj;
  
      public Decorator(BaseDrink obj) {
          this.obj = obj;
      }
  
      @Override
      public float cost() {
          return super.getPrice() + obj.cost();
      }
  
      @Override
      public String getDescription() {
          return super.description + "-" + super.getPrice() + "&&" + obj.getDescription();
      }
  
  }
  ```

- 接着我们就可以基于上面的类来设计一杯低咖,设置好单价

  ```java
  public class Decaf extends Coffee {
  
      public Decaf(Integer amount) {
          super.setDescription("Decaf");
          super.setAmount(amount);
          super.setPrice(3.0f);
      }
  
  }
  ```

- 再来点热巧克力，设置好单价

  ```java
  public class Chocolate extends Decorator {
  
      public Chocolate(BaseDrink obj) {
          super(obj);
          super.setDescription("Chocolate");
          super.setPrice(3.0f);
      }
  
  }
  ```

- 再来点牛奶，设置单价

  ```java
  public class Milk extends Decorator {
  
      public Milk(BaseDrink obj) {
          super(obj);
          super.setDescription("Milk");
          super.setPrice(2.0f);
      }
  
  }
  ```

- 好了 咱们来测试一下效果

  ```java
  public class CoffeeBar {
  
      public static void main(String[] args) {
          BaseDrink order;
          System.out.println("****************");
          order = new Decaf(1);
          order = new Milk(order);
          order = new Chocolate(order);
          order = new Chocolate(order);
          System.out.println("order1 desc:" + order.getDescription());
          System.out.println("order1 price:" + order.cost());
          System.out.println("****************");
      }
  }
  ```

- 输出结果

  ```tex
  ****************
  order1 desc:Chocolate-3.0&&Chocolate-3.0&&Milk-2.0&&Decaf-3.0 
  order1 price:11.0
  **************** 
  ```

看到结果，小王一脸崇拜的看着老李，老李脑袋在灯光下冉冉发光，形象无比的高大。。。。

#### 扩展

其实java内置的IO对象里面也使用到了装饰器模式，下面代码就是我们用来测试的

- 我们新建一个UpperCaseInputStream类让它继承FilterInputStream并重写其中的read方法

```java
public class UpperCaseInputStream extends FilterInputStream {

    protected UpperCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toUpperCase((char) (c));
    }

    @Override
    public int read(byte[] b, int offset, int len) throws IOException {
        int result = super.read(b, offset, len);
        for (int i = 0; i < result; i++) {
            b[i] = (byte) Character.toUpperCase((char) (b[i]));
        }
        return result;
    }
}
```

- 可以看看FilterInputStream源码继承了InputStream

```java
 /* @author  Jonathan Payne
 * @since   JDK1.0
 */
public
class FilterInputStream extends InputStream {....}
```

- 测试方法

  ```java
  public class InputTest {
      public static void main(String[] args) {
          int c;
          try {
              InputStream in = new UpperCaseInputStream(new BufferedInputStream(new FileInputStream("F:\\test.txt")));
              while ((c = in.read()) >= 0) {
                  System.out.print((char) c);
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
  
      }
  }
  ```

  

#### 总结

一般的情况下我们为了扩展一个子类，经常使用装饰者模式实现，由于继承为类引入静态特征，并且随着扩展功能的增多，子类会很膨胀所以在不想增加很多子类的情况下我们可以考虑使用装饰器模式，其中装饰类和被装饰类可以独立发展，不会互相耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能。



#### 链接

```tex
本文代码仓库地址：https://gitee.com/singlekingdom/JavaDesignPatterns.git
```
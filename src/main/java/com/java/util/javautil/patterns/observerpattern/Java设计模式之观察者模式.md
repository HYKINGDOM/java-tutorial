### Java设计模式之观察者模式

**本文仅是个人观点，如有错误请指正**

### 简介

当对象间存在一对多关系时，可以考虑使用观察者模式（Observer Pattern）。比如，当一个对象被修改时，则会自动通知它的依赖对象。观察者模式属于行为型模式。

### 实现

接下来将用三个例子来说明观察者模式，详细代码可以参考文末的地址。

#### 需求

现在有一个气象站，想对外发布数据，有一个公司知道后想要通过天气站的数据建立一个天气看板，这就交给了公司的开发小王，小王拿到需求后，很快就开始行动了，下面就是小王的实现方法

* 气象站类，主要提供温度、压力、湿度参数，以及数据更新，数据通知，再把需要接收参数的看板加进来指定通知对象。

  ```java
  public class WeatherData {
  
      /**
       * 温度
       */
      private float mTemperate;
  
      /**
       * 压力
       */
      private float mPressure;
  
      /**
       * 湿度
       */
      private float mHumidity;
  
      private CurrentConditions mCurrentConditions;
  
      /**
       * 加入需要通知的看板
       * @param mCurrentConditions
       */
      public WeatherData(CurrentConditions mCurrentConditions) {
          this.mCurrentConditions = mCurrentConditions;
      }
  
      public float getTemperature() {
          return mTemperate;
  
      }
  
      public float getPressure() {
          return mPressure;
  
      }
  
      public float getHumidity() {
          return mHumidity;
  
      }
  
      /**
       * 通知数据修改
       */
      public void dataChange() {
          mCurrentConditions.update(getTemperature(), getPressure(), getHumidity());
      }
  
      /**
       * 数据修改 同时调用通知方法
       *
       * @param mTemperature
       * @param mPressure
       * @param mHumidity
       */
      public void setData(float mTemperature, float mPressure, float mHumidity) {
          this.mTemperate = mTemperature;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
          dataChange();
      }
  
  }
  ```

  

* 然后就是温度看板，获取当前的温度，两个方法，一个接收数据更新，一个展示数据。

  ```java
  public class CurrentConditions {
  
      private float mTemperature;
      private float mPressure;
      private float mHumidity;
  
      public void update(float mTemperature, float mPressure, float mHumidity) {
          this.mTemperature = mTemperature;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
          display();
      }
  
      public void display() {
          System.out.println("***Today mTemperature: " + mTemperature + "***");
          System.out.println("***Today mPressure: " + mPressure + "***");
          System.out.println("***Today mHumidity: " + mHumidity + "***");
      }
  }
  ```

* 好了咱们开始测试一下看看效果如何

  ```java
  public class InternetWeather {
  
      public static void main(String[] args) {
          CurrentConditions mCurrentConditions = new CurrentConditions();
          WeatherData mWeatherData = new WeatherData(mCurrentConditions);
          mWeatherData.setData(30, 150, 40);
      }
  }
  
  ```

* 执行结果

  ```wiki
  ***Today mTemperature: 30.0***
  ***Today mPressure: 150.0***
  ***Today mHumidity: 40.0***
  ```

好了看起来需求已经满足了，开始使用吧，然而没过多久，另外一个公司也和天气站联系上了，想要他们提供的天气数据来展示明天的天气，小王一听便说那还不简单，去weather类里面加上你的看板类，加上通知，加上。。

不对啊，这加一个使用者就这么麻烦了，那以后要是来了更多的使用者怎么办？又或者有的使用者突然又不使用这个数据了，想要取消通知怎么办？要是按这样的话改来改去每次修改都要重新上线，会非常麻烦，数据的及时性也得不到保证。小王头疼不已，这时候公司里的老李看到了，笑呵呵的说小王，不要着急，我们再重新看看你写的方法，再想想需要加入的接口和你有什么共同的地方，咱们把它抽离出去，然后再。。。。。小王在老李的帮助下很快做出了第二版实现

* 首先我们加入数据提供方接口，将我们之前提到了的，新增需求方，删除需求方，以及通知加入进去

  ```java
  public interface Subject {
  	/**
  	 * 注册观察者
  	 * @param o
  	 */
  	void registerObserver(Observer o);
  
  	/**
  	 * 移除观察者
  	 * @param o
  	 */
  	void removeObserver(Observer o);
  
  	/**
  	 * 通知观察者
  	 */
  	void notifyObservers();
  }
  ```

* 接着就是需求方的接口，主要用来获取数据

  ```java
  public interface Observer {
  
      /**
       * 数据更新
       * @param mTemperate
       * @param mPressure
       * @param mHumidity
       */
      void update(float mTemperate, float mPressure, float mHumidity);
  }
  ```

* 接着修改之前的天气数据类，在这里面我们实现前面的数据提供方的接口，然后重写里面的方法，注册接口我们暂时使用list作为存储观察者的集合，移除接口就是移除集合中的观察者，这里我们检查一下观察者是否存在集合中再移除，然后是通知接口，我们遍历集合逐个通知需求方。（这里我们是将数据一个一个的传给使用方）。

  ```java
  public class WeatherDataSt implements Subject {
  
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
      private List<Observer> mObservers;
  
      public WeatherDataSt() {
          mObservers = new ArrayList<>();
      }
  
      public float getTemperature() {
          return mTemperate;
  
      }
  
      public float getPressure() {
          return mPressure;
  
      }
  
      public float getHumidity() {
          return mHumidity;
  
      }
  
      public void dataChange() {
          notifyObservers();
      }
  
  
      public void setData(float mTemperate, float mPressure, float mHumidity) {
          this.mTemperate = mTemperate;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
          dataChange();
      }
  
      @Override
      public void registerObserver(Observer o) {
          mObservers.add(o);
      }
  
      @Override
      public void removeObserver(Observer observer) {
          if (mObservers.contains(observer)) {
              mObservers.remove(observer);
          }
      }
  
      @Override
      public void notifyObservers() {
          for (Observer mObserver : mObservers) {
              mObserver.update(getTemperature(), getPressure(), getHumidity());
              System.out.println("开始通知：" + mObserver.getClass().getSimpleName());
          }
      }
  
  }
  ```

* 天气看板中实现观察者接口,实现当中的数据接口

  ```java
  public class CurrentConditions implements Observer {
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
  
      @Override
      public void update(float mTemperate, float mPressure, float mHumidity) {
          this.mHumidity = mHumidity;
          this.mPressure = mPressure;
          this.mTemperate = mTemperate;
          display();
      }
  
      public void display() {
          System.out.println("***Today mTemperate:" + mTemperate + "***");
          System.out.println("***Today mPressure:" + mPressure + "***");
          System.out.println("***Today mHumidity:" + mHumidity + "***");
      }
  
  }
  ```

* 在查看明天的天气面板实现观察者接口

  ```java
  public class ForcesConditions implements Observer {
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
  
      @Override
      public void update(float mTemperate, float mPressure, float mHumidity) {
          this.mTemperate = mTemperate;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
          display();
      }
  
      public void display() {
          System.out.println("**明天温度:" + (mTemperate + Math.random()) + "**");
          System.out.println("**明天气压:" + (mPressure + 10 * Math.random()) + "**");
          System.out.println("**明天湿度:" + (mHumidity + Math.random()) + "**");
      }
  }
  
  ```

* 然后我们编写测试类

  ```java
  public class InternetWeather {
  
      public static void main(String[] args) {
  
          CurrentConditions mCurrentConditions= new CurrentConditions();
          ForcesConditions mForcesConditions = new ForcesConditions();
          WeatherDataSt mWeatherDataSt = new WeatherDataSt();
  
          //注册天气看板
          mWeatherDataSt.registerObserver(mCurrentConditions);
          mWeatherDataSt.registerObserver(mForcesConditions);
  
          mWeatherDataSt.setData(30, 150, 40);
          //移除看板
          mWeatherDataSt.removeObserver(mCurrentConditions);
          mWeatherDataSt.setData(40, 250, 50);
      }
  
  }
  
  ```

* 打印结果

  ```tex
  ***Today mTemperate:30.0***
  ***Today mPressure:150.0***
  ***Today mHumidity:40.0***
  开始通知：CurrentConditions
  **明天温度:30.708855560149452**
  **明天气压:156.09944964615488**
  **明天湿度:40.252322896560706**
  开始通知：ForcesConditions
  **明天温度:40.194891069978894**
  **明天气压:253.89775138044408**
  **明天湿度:50.95728186211783**
  开始通知：ForcesConditions
  ```

我们看到经过老李的一番指导，现在的设计基本上满足了需求，即使新增看板继续继承观察者接口，不需要的观察者就可以直接移除，这样提高了扩展性，去除了之前代码每次新增需要修改代码的弊端，实现了开闭原则。

接着老李又说，小王啊，其实java内置对象中就已经实现了观察者模式我们只需要拿来用就可以了。。。

怎么用呢？请看下面的实现。

* 天气数据，这里我们不再实现subject接口，反而继承java内置对象Observable，在内置对象中已经帮我们实现了通知，注册，移除等方法，所以我们只需要写我们自定义的方法就行了。

  ```
  public class WeatherData extends Observable {
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
  
      public float getTemperature() {
          return mTemperate;
  
      }
  
      public float getPressure() {
          return mPressure;
  
      }
  
      public float getHumidity() {
          return mHumidity;
  
      }
  
      /**
       * 通知数据已修改
       */
      public void dataChange() {
          //Observable的源码中setChanged()，设置changed为true表示数据已经修改，只有当changed为true的时候notifyObservers()方法才会通知修改
          this.setChanged();
          this.notifyObservers(new AssembleWeatherData(getTemperature(), getPressure(), getHumidity()));
  
      }
  
      /**
       * 修改数据
       * @param mTemperate
       * @param mPressure
       * @param mHumidity
       */
      public void setData(float mTemperate, float mPressure, float mHumidity) {
          this.mTemperate = mTemperate;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
          dataChange();
      }
  
  }
  ```

* 同样天气看板类只用实现Observer接口，重写当中的update方法就行了

  ```java
  public class CurrentConditions implements Observer {
  
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
  
      @Override
      public void update(Observable arg0, Object arg1) {
          this.mTemperate = ((AssembleWeatherData) (arg1)).mTemperate;
          this.mPressure = ((AssembleWeatherData) (arg1)).mPressure;
          this.mHumidity = ((AssembleWeatherData) (arg1)).mHumidity;
          display();
      }
  
      public void display() {
          System.out.println("***Today mTemperate:" + mTemperate + "***");
          System.out.println("***Today mPressure:" + mPressure + "***");
          System.out.println("***Today mHumidity:" + mHumidity + "***");
      }
  }
  ```

* 另外一个天气看板，一样的实现

  ```java
  public class ForecastConditions implements Observer {
  
      private float mTemperate;
      private float mPressure;
      private float mHumidity;
  
      /**
       * 数据更新 同时调用展示数据方法
       *
       * @param arg0
       * @param arg1
       */
      @Override
      public void update(Observable arg0, Object arg1) {
          this.mTemperate = ((AssembleWeatherData) (arg1)).mTemperate;
          this.mPressure = ((AssembleWeatherData) (arg1)).mPressure;
          this.mHumidity = ((AssembleWeatherData) (arg1)).mHumidity;
          display();
      }
  
      /**
       * 展示数据
       */
      public void display() {
          System.out.println("**明天温度:" + (mTemperate + Math.random()) + "**");
          System.out.println("**明天气压:" + (mPressure + 10 * Math.random()) + "**");
          System.out.println("**明天湿度:" + (mHumidity + Math.random()) + "**");
      }
  
  
  }
  ```

* 为了方便传递数据，我们再新建一个AssembleWeatherData类，用于设计返回数据内容

  ```java
  public class AssembleWeatherData {
      public float mTemperate;
      public float mPressure;
      public float mHumidity;
  
      public AssembleWeatherData(float mTemperate, float mPressure, float mHumidity) {
          this.mTemperate = mTemperate;
          this.mPressure = mPressure;
          this.mHumidity = mHumidity;
      }
  }
  ```

* 接着我们新建测试类

  ```java
  public class InternetWeather {
      
      public static void main(String[] args) {
          CurrentConditions mCurrentConditions;
          ForecastConditions mForecastConditions;
          WeatherData mWeatherData;
  
          mCurrentConditions = new CurrentConditions();
          mForecastConditions = new ForecastConditions();
          mWeatherData = new WeatherData();
  
          //java内置观察者模式中的addObserver方法，用于注册观察者
          mWeatherData.addObserver(mCurrentConditions);
          mWeatherData.addObserver(mForecastConditions);
          mWeatherData.setData(30, 150, 40);
  
          //内置方法，用于移除观察者，重新修改数据
          mWeatherData.deleteObserver(mCurrentConditions);
          mWeatherData.setData(35, 150, 60);
  
      }
  }
  ```

* 打印数据

  ```
  **明天温度:30.676433372455936**
  **明天气压:154.2686999140749**
  **明天湿度:40.015042461544155**
  ***Today mTemperate:30.0***
  ***Today mPressure:150.0***
  ***Today mHumidity:40.0***
  **明天温度:35.4090594721898**
  **明天气压:157.28459919820716**
  **明天湿度:60.286781430877525**
  ```

果然经过老李一番重构，小王很快就明白了，观察者模式的好处，看着老李地中海的脑袋心想，这或许就是成为大牛的代价吧。。。。



### 总结

前面讲述了两种观察者模式的实现方法，一个是我们自己写的接口做的实现，另一种是直接使用java中的内置观察者模式实现，两者各有各的优势和缺点，对于一般的情况内置观察者就能满足，但是有一点Observable是一个类不是接口所以只能继承他，但由于java只能对接口多继承，所以使用内置对象的时候需要多继承的时候就不能使用了，自定义的方法可以更灵活，但是需要写更多的代码，鱼和熊掌不可兼得。



### 链接

本文代码仓库地址：https://gitee.com/singlekingdom/JavaDesignPatterns.git
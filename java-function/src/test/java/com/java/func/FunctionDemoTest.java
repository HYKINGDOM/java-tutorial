package com.java.func;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class FunctionDemoTest {

    private static void applyFunction(Function func, String p) {
        System.out.println("Function 的执行结果: " + func.apply(p));
    }

    private static void acceptConsumer(Consumer consumer, String p) {
        consumer.accept(p);
    }

    @Test
    public void test_Function() {
        Function<String, Integer> strLen = new Function<>() {

            @Override
            public Integer apply(String t) {
                return t.length();
            }

        };
        System.out.println("Function 示例, 字符串长度: " + strLen.apply("Hello"));
    }

    @Test
    public void test_Function01() {
        Function<String, String> hello = new Function<>() {
            @Override
            public String apply(String t) {
                return "Hello, " + t;
            }
        };

        Function<String, Integer> counter = new Function<>() {

            @Override
            public Integer apply(String t) {
                return t.length();
            }

        };

        //生成一个组合函数, 该函数首先将当前 Function 作为其输入, 然后将 after 函数应用于输入后产生的结果.
        Function<String, Integer> couterOfHello = hello.andThen(counter);
        String param = "阿文";
        System.out.println("hello 函数带'" + param + "'参数,  生成的字符串的长度: " + couterOfHello.apply(param));

        param = "Java";
        System.out.println("hello 函数带'" + param + "'参数,  生成的字符串的长度: " + couterOfHello.apply(param));
    }

    @Test
    public void test_Function01_01() {
        Function<String, String> hello = new Function<>() {
            @Override
            public String apply(String t) {
                return "Hello, " + t;
            }
        };

        Function<String, String> counter = new Function<>() {

            @Override
            public String apply(String t) {
                return String.valueOf(t.length());
            }

        };

        //生成一个组合函数, 该函数首先将当前 Function 作为其输入, 然后将 after 函数应用于输入后产生的结果.
        Function<String, String> couterOfHello = hello.compose(counter);
        String param = "阿文";
        System.out.println("hello 函数带'" + param + "'参数,  生成的字符串的长度: " + couterOfHello.apply(param));

        param = "Java";
        System.out.println("hello 函数带'" + param + "'参数,  生成的字符串的长度: " + couterOfHello.apply(param));
    }

    @Test
    public void test_Function02() {
        Function<String, String> hello = new Function<>() {
            @Override
            public String apply(String s) {
                if (s.equals("")) {
                    return "";
                } else {
                    return "Hello, " + s;
                }
            }
        };

        //超过5个字符将无法通过此过滤器
        Function<String, String> longerThanFiveFilter = new Function<>() {
            @Override
            public String apply(String t) {
                return t.length() > 5 ? "" : t + "!";
            }

        };

        //生成一个组合函数, 该函数首先将 before 函数应用于当前函数的输入, 然后将当前函数应用于结果.
        Function<String, String> filterTooLong = hello.compose(longerThanFiveFilter);

        System.out.println("超过5个字符的将不会显示." + filterTooLong.apply("Aristotle"));

        System.out.println("正常调用:");
        System.out.println(filterTooLong.apply("Test"));

        System.out.println("实际执行过程:");
        String r = longerThanFiveFilter.apply("Jack");
        System.out.println(hello.apply(r));
    }

    @Test
    public void test_Function04() {
        Function<String, String> idHello = Function.identity();

        String param = "Jack";
        System.out.println("是否总是相等? " + idHello.apply(param).equals(param));

        param = "猫猫";
        System.out.println("是否总是相等? " + idHello.apply(param).equals(param));
    }

    @Test
    public void test_BiFunction() {
        BiFunction<String, Integer, Boolean> expectLength = new BiFunction<>() {
            @Override
            public Boolean apply(String t, Integer u) {
                return t.length() == u;
            }
        };
        System.out.print("BiFunction 示例, 字符串长度是否符合预期? " + expectLength.apply("21yi", 4));
    }

    @Test
    public void test_BiFunction01() {
        BiFunction<String, Integer, String> hello = new BiFunction<>() {
            @Override
            public String apply(String t, Integer u) {
                return "Hello, I'm " + t + ", " + u + " year(s) old.";
            }
        };

        Function<String, String> counter = new Function<>() {

            @Override
            public String apply(String t) {
                return "'" + t + "', 字符总长: " + t.length();
            }

        };

        BiFunction<String, Integer, String> analyse = hello.andThen(counter);
        String param = "阿文";
        System.out.println(analyse.apply(param, 18));

        param = "Java";
        System.out.println(analyse.apply(param, 16));
    }

    /**
     * consumer接口测试
     */
    @Test
    public void test_Consumer() {
        //① 使用consumer接口实现方法
        Consumer<String> consumer = new Consumer<String>() {

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        Stream<String> stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        stream.forEach(consumer);

        System.out.println("********************");

        //② 使用lambda表达式，forEach方法需要的就是一个Consumer接口
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer<String> consumer1 = (s) -> System.out.println(s);//lambda表达式返回的就是一个Consumer接口
        stream.forEach(consumer1);
        //更直接的方式
        //stream.forEach((s) -> System.out.println(s));
        System.out.println("********************");

        //③ 使用方法引用，方法引用也是一个consumer
        stream = Stream.of("aaa", "bbb", "ddd", "ccc", "fff");
        Consumer consumer2 = System.out::println;
        stream.forEach(consumer);
        //更直接的方式
        //stream.forEach(System.out::println);
    }

    @Test
    public void test_Consumer_01() {
        Consumer<String> question = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println("A: 请问您是 " + t + " 吗?");
            }
        };

        Consumer<String> answer = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println("B: 是的, 我是 " + t);
            }
        };

        Consumer<String> confirm = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println("A: 啊? 真的嘛? 您真的是 " + t + " 吗?");
            }
        };

        Consumer<String> angery = new Consumer<String>() {
            @Override
            public void accept(String t) {
                System.out.println("B: 当然了, 我就是 " + t + ". 你要我说几遍?");
            }
        };

        //组合的 Consumer 公用了相同的输入
        Consumer<String> dialogue = question.andThen(answer).andThen(confirm).andThen(angery);
        dialogue.accept("21yi");

        System.out.println("-".repeat(40));

        dialogue.accept("谁谁谁");
    }

    @Test
    public void test_BiConsumer() {
        BiConsumer<String, Integer> hello = new BiConsumer<>() {
            @Override
            public void accept(String name, Integer age) {
                System.out.println("你好, 我叫" + name + ", 今年 " + age + " 岁了.");
            }
        };
        System.out.print("BiConsumer 示例");
        hello.accept("21yi", 1);
    }

    @Test
    public void test_BiConsumer01() {
        BiConsumer<String, String> say = new BiConsumer<>() {
            @Override
            public void accept(String t, String u) {
                System.out.println("A: 你" + u + "了, " + t);
            }
        };

        BiConsumer<String, String> no = new BiConsumer<>() {
            @Override
            public void accept(String t, String u) {
                System.out.println("B: 是的, 我" + u + "了, 但请叫我 达" + t + " !");
            }
        };

        BiConsumer<String, String> still = new BiConsumer<>() {
            @Override
            public void accept(String t, String u) {
                System.out.println("A: 好的, " + t + "; 没问题, " + t + "; 你" + u + "好厉害啊, " + t);
            }
        };

        //组合的 Consumer 公用了相同的输入
        BiConsumer<String, String> dialogue = say.andThen(no).andThen(still);
        dialogue.accept("文西", "受伤");

        System.out.println("-".repeat(40));

        dialogue.accept("21yi", "学编程");
    }

    @Test
    public void test_Supplier() {
        //① 使用Supplier接口实现方法,只有一个get方法，无参数，返回一个值
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                //返回一个随机值
                return RandomUtil.randomInt();
            }
        };

        System.out.println(supplier.get());

        System.out.println("********************");

        //② 使用lambda表达式，
        supplier = RandomUtil::randomInt;
        System.out.println(supplier.get());
        System.out.println("********************");

        //③ 使用方法引用
        Supplier<Double> supplier2 = Math::random;
        System.out.println(supplier2.get());
    }

    /**
     * Predicate谓词测试，谓词其实就是一个判断的作用类似bool的作用
     */
    @Test
    public void test_Predicate() {
        //① 使用Predicate接口实现方法,只有一个test方法，传入一个参数，返回一个bool值
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                if (integer > 5) {
                    return true;
                }
                return false;
            }
        };

        System.out.println(predicate.test(6));

        System.out.println("********************");

        //② 使用lambda表达式，
        predicate = (t) -> t > 5;
        System.out.println(predicate.test(1));
        System.out.println("********************");

    }

    @Test
    public void test_Predicate01() {
        Predicate<Double> isDoubleMax = new Predicate<>() {
            @Override
            public boolean test(Double d) {
                return d.equals(Double.MAX_VALUE);
            }
        };
        System.out.println("Predicate 示例, 是否 Double 的最大值? " + isDoubleMax.test(Double.MAX_VALUE));
    }

    @Test
    public void test_Predicate02() {
        Predicate<Person> isNoname = new Predicate<>() {
            @Override
            public boolean test(Person person) {
                return person.getName() == null || "".equals(person.getName());
            }
        };

        Predicate<Person> isBaby = new Predicate<>() {
            @Override
            public boolean test(Person person) {
                System.out.println("info: Age - " + person.getAge());
                return person.getAge() <= 3;
            }
        };

        Predicate<Person> isNonameBaby = isNoname.and(isBaby);

        Person p1 = new Person(null, 18);
        Person p2 = new Person("21yi", 1);
        Person p3 = new Person("", 1);

        System.out.println("p1 是否无名的小宝宝? " + isNonameBaby.test(p1));
        System.out.println("p2 是否无名的小宝宝? " + isNonameBaby.test(p2));
        System.out.println("p3 是否无名的小宝宝? " + isNonameBaby.test(p3));
    }

    @Test
    public void test_Predicate03() {
        Predicate<String> helloStringEquals = Predicate.isEqual("Hello");
        String obj = "Hello";
        System.out.println("Hello 和 " + obj + " 是否相等? " + helloStringEquals.test(obj));
        obj = "hello";
        System.out.println("Hello 和 " + obj + " 是否相等? " + helloStringEquals.test(obj));
    }

    @Test
    public void test_Predicate04() {
        Predicate<String> equals21YI = Predicate.isEqual("21YI");
        String obj = "21YI";
        System.out.println("21YI 和 " + obj + " 是否 相等? " + equals21YI.test(obj));

        Predicate<String> notEquals21YI = equals21YI.negate();
        obj = "21yi";
        System.out.println("21YI 和 " + obj + " 是否 不等? " + notEquals21YI.test(obj));

        Predicate<Object> notEmptyString = Predicate.isEqual("").negate();
        System.out.println("不是空字符串? " + notEmptyString.test("Hello"));
    }

    @Test
    public void test_Predicate05() {
        Predicate<Person> isNoname = new Predicate<>() {
            @Override
            public boolean test(Person person) {
                return person.getName() == null || "".equals(person.getName());
            }
        };

        Predicate<Person> isBaby = new Predicate<>() {
            @Override
            public boolean test(Person person) {
                System.out.println("info: Age - " + person.getAge());
                return person.getAge() <= 3;
            }
        };

        Predicate<Person> isNonameOrBaby = isNoname.or(isBaby);

        Person p1 = new Person(null, 18);
        Person p2 = new Person("娃娃", 1);
        Person p3 = new Person("21yi", 18);

        System.out.println("p1 是否无名或小宝宝? " + isNonameOrBaby.test(p1));
        System.out.println("p2 是否无名或小宝宝? " + isNonameOrBaby.test(p2));
        System.out.println("p3 是否无名或小宝宝? " + isNonameOrBaby.test(p3));
    }

    @Test
    public void test_UnaryOperator() {
        UnaryOperator<String> maskTel = new UnaryOperator<>() {
            @Override
            public String apply(String tel) {
                if (tel.length() < 8) {
                    return tel;
                }
                return tel.substring(0, 2) + "****" + tel.substring(6, tel.length());
            }
        };
        System.out.println("UnaryOperator 示例, 掩盖电话: " + maskTel.apply("1234567890"));
    }

    @Test
    public void test_UnaryOperator01() {
        UnaryOperator<String> idHello = UnaryOperator.identity();

        String param = "Jack789789515";
        System.out.println("是否总是相等? " + idHello.apply(applyUnaryOperator(param)).equals(param));

        UnaryOperator<Integer> idHello2 = UnaryOperator.identity();

        Integer ten = 10;
        System.out.println("是否总是相等? " + idHello2.apply(ten).equals(ten));
    }

    private String applyUnaryOperator(String tel) {
        if (tel.length() < 8) {
            return tel;
        }
        return tel.substring(0, 2) + "****" + tel.substring(6, tel.length());
    }

    @Test
    public void test_FunctionalProgramming() {

        Function<String, Integer> strLen = new Function<>() {

            @Override
            public Integer apply(String t) {
                return t.length();
            }

        };
        System.out.println("Function 示例, 字符串长度: " + strLen.apply("Hello"));

        Consumer<String> sayHello = new Consumer<>() {
            @Override
            public void accept(String t) {
                System.out.println("Consumer 示例: 你好, " + t);
            }
        };
        sayHello.accept("21yi");

        Supplier<Integer> randSupplier = new Supplier<>() {
            @Override
            public Integer get() {
                return (int)(Math.random() * 100);
            }
        };
        System.out.println("Supplier 示例, 1-100随机数: " + randSupplier.get());

        Predicate<Double> isDoubleMax = new Predicate<>() {
            @Override
            public boolean test(Double d) {
                return d.equals(Double.MAX_VALUE);
            }
        };
        System.out.println("Predicate 示例, 是否 Double 的最大值? " + isDoubleMax.test(Double.MAX_VALUE));

        UnaryOperator<String> maskTel = new UnaryOperator<>() {
            @Override
            public String apply(String tel) {
                if (tel.length() < 8) {
                    return tel;
                }
                return tel.substring(0, 2) + "****" + tel.substring(6, tel.length());
            }
        };
        System.out.println("UnaryOperator 示例, 掩盖电话: " + maskTel.apply("1234567890"));

        BiConsumer<String, Integer> hello = new BiConsumer<>() {
            @Override
            public void accept(String name, Integer age) {
                System.out.println("你好, 我叫" + name + ", 今年 " + age + " 岁了.");
            }
        };
        System.out.print("BiConsumer 示例");
        hello.accept("21yi", 1);

        BiFunction<String, Integer, Boolean> expectLength = new BiFunction<>() {
            @Override
            public Boolean apply(String t, Integer u) {
                return t.length() == u;
            }
        };
        System.out.println("BiFunction 示例, 字符串长度是否符合预期? " + expectLength.apply("21yi", 4));

        applyFunction((s) -> {
            return s.toString().length();
        }, "21yi");

        acceptConsumer(System.out::println, "Hello, 21yi");

    }

    private static class Person {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

    }

}

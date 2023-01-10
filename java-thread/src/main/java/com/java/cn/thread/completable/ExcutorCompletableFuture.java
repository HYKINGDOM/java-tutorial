package com.java.cn.thread.completable;

import cn.hutool.core.util.RandomUtil;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ExcutorCompletableFuture {

    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end ...");
        });
        future.get();
    }

    //有返回值
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = " + time);
    }

    public static void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
            }
            System.out.println("run end ...");
        });

        future.whenComplete((t, action) -> System.out.println("执行完成！"));
        future.exceptionally(t -> {
            System.out.println("执行失败！" + t.getMessage());
            return null;
        });

        TimeUnit.SECONDS.sleep(2);
    }

    public static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            System.out.println("result1=" + result);
            return result;
        }).thenApply(t -> {
            long result = t * 5;
            System.out.println("result2=" + result);
            return result;
        });

        long result = future.get();
        System.out.println(result);
    }


    public static void handle() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i = 10 / 0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if (throwable == null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });
        System.out.println(future.get());
    }

    public static void thenAccept() throws Exception {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> new Random().nextInt(10))
                .thenAccept(System.out::println);
        future.get();
    }

    public static void thenAcceptAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> new Random().nextInt(10))
                .thenAcceptAsync(System.out::println);
        future.get();
    }


    public static void thenRun() throws Exception {
        CompletableFuture<Void> future = CompletableFuture
                .supplyAsync(() -> {
                    int nextInt = new Random().nextInt(10);
                    System.out.println(nextInt);
                    return nextInt;
                })
                .thenRun(() -> System.out.println("thenRun ..."));
        future.get();
    }

    public static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello future1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello future2");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t + " " + u);
        System.out.println(result.get());
    }


    public static void thenAcceptBoth() {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("f2=" + t);
            return t;
        });
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1=" + t + ";f2=" + u + ";"));
    }

    public static void applyToEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println(t);
            return t * 2;
        });
        System.out.println(result.get());
    }

    public static void acceptEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        System.out.println(f1.acceptEither(f2, System.out::println).get());
    }


    public static void runAfterEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });
        System.out.println(f1.runAfterEither(f2, () -> System.out.println("有一个任务已经完成了。")).get());
    }


    public static void acceptEitherAsync() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = RandomUtil.randomInt(5, 10);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = RandomUtil.randomInt(1, 5);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });

        Consumer<Object> objectConsumer = o -> System.out.println("有一个任务已经完成了。 返回参数为： " + o.toString());

        CompletableFuture<Void> voidCompletableFuture = f1.acceptEitherAsync(f2, objectConsumer);

        System.out.println(voidCompletableFuture.get());
    }


    public static void runAfterBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1=" + t);
            return t;
        });

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2=" + t);
            return t;
        });


        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f3=" + t);
            return t;
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 t2 都执行完成了。");
            }
        };

        CompletableFuture<Void> voidCompletableFuture = f1.runAfterBoth(f2, runnable).runAfterBoth(f3, () -> System.out.println("t3 都执行完成了。"));
        System.out.println(voidCompletableFuture.get());
    }


    public static void thenCompose() throws Exception {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            System.out.println("t1=" + t);
            return t;
        });

        future01.thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }));


        future01.thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 10;
            System.out.println("t3=" + t);
            return t;
        }));


        System.out.println("thenCompose result : " + future01.get());
    }


    public static void thenComposeAsync() throws Exception {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            System.out.println("t1=" + t);
            return t;
        });

        CompletableFuture<Integer> future02 = future01.thenComposeAsync(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }));


        CompletableFuture<Integer> future03 = future01.thenComposeAsync(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 10;
            System.out.println("t3=" + t);
            return t;
        }));


        System.out.println("thenCompose future01 result : " + future01.get());
        System.out.println("thenCompose future02 result : " + future02.get());
        System.out.println("thenCompose future03 result : " + future03.get());
    }

    public static void thenComposeAsyncExecutor() throws Exception {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(10);
            System.out.println("t1=" + t);
            return t;
        });

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future02 = future01.thenComposeAsync(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 2;
            System.out.println("t2=" + t);
            return t;
        }), executorService);


        CompletableFuture<Integer> future03 = future01.thenComposeAsync(param -> CompletableFuture.supplyAsync(() -> {
            int t = param * 10;
            System.out.println("t3=" + t);
            return t;
        }),executorService);


        System.out.println("thenCompose future01 result : " + future01.get());
        System.out.println("thenCompose future02 result : " + future02.get());
        System.out.println("thenCompose future03 result : " + future03.get());
    }
}

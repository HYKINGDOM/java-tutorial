package com.java.cn.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.*;

public class MainThread {

//    private ThreadFactory writeThreadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-Pool-%d").build();
//
//    private ExecutorService singleThreadPool = new ThreadPoolExecutor(2,5,0L,
//            TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(1024),writeThreadFactory,new ThreadPoolExecutor.AbortPolicy());


    @Test
    public void test_str(){
        System.out.println('a'>'A'?'b':'B');
    }




    public static void main(String[] args) {
        ThreadFactory writeThreadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-Pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1,5,0L,
                TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(1024),writeThreadFactory,new ThreadPoolExecutor.AbortPolicy());

        MainThread mainThread = new MainThread();
//        singleThreadPool.submit(() -> {
//            while (true){
//                System.out.println("========线程2.1运行=========");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        singleThreadPool.submit(() -> {
//            while (true){
//                System.out.println("========线程4.1运行=========");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        singleThreadPool.submit(() -> {
//            while (true){
//                System.out.println("========线程3.1运行=========");
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        singleThreadPool.submit(mainThread::test_thread_1_new);
        singleThreadPool.submit(mainThread::test_thread_2_new);
        singleThreadPool.execute(mainThread::test_thread_1_new);
        singleThreadPool.execute(mainThread::test_thread_2_new);

    }




    public void test_thread_1_new(){
        System.out.println("========线程1运行=========");
        while (true){
            System.out.println("========线程1运行=========");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test_thread_2_new(){
        System.out.println("========线程2运行=========");
        while (true){
            System.out.println("========线程2运行=========");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void test_thread_3_new(){
        System.out.println("========线程3运行=========");
        while (true){
            System.out.println("========线程3运行=========");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void existsDelete(String dirPath,String fileName) {
        File pathFile = new File(dirPath);
        if(!pathFile.exists() || pathFile.isFile()) {
            return;
        }
        for(File file:pathFile.listFiles()) {
            if(file.isFile() && fileName.equals(file.getName())) {
                file.delete();
                break;
            }
        }
    }

}

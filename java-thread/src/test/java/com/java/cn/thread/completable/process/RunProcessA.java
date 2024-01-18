package com.java.cn.thread.completable.process;

public class RunProcessA implements RunProcess {
    @Override
    public Integer process(Integer a) {

        try {

            Thread.sleep(1000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }


        return a + 3;
    }
}

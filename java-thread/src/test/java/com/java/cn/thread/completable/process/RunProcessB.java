package com.java.cn.thread.completable.process;

public class RunProcessB implements RunProcess {
    @Override
    public Integer process(Integer a) {

        try {

            Thread.sleep(3000);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return a + 9;
    }
}

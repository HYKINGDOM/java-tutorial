package com.java.tutorial.project.seq;



import com.java.tutorial.project.async.callback.ICallback;
import com.java.tutorial.project.async.callback.IWorker;
import com.java.tutorial.project.async.executor.timer.SystemClock;
import com.java.tutorial.project.async.worker.WorkResult;
import com.java.tutorial.project.async.wrapper.WorkerWrapper;

import java.util.Map;

/**
 * @author hy
 */
public class SeqWorker implements IWorker<String, String>, ICallback<String, String> {

    @Override
    public String action(String object, Map<String, WorkerWrapper> allWrappers) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result = " + SystemClock.now() + "---param = " + object + " from 0";
    }


    @Override
    public String defaultValue() {
        return "worker0--default";
    }

    @Override
    public void begin() {
        //System.out.println(Thread.currentThread().getName() + "- start --" + System.currentTimeMillis());
    }

    @Override
    public void result(boolean success, String param, WorkResult<String> workResult) {
        if (success) {
            System.out.println("callback worker0 success--" + SystemClock.now() + "----" + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        } else {
            System.err.println("callback worker0 failure--" + SystemClock.now() + "----"  + workResult.getResult()
                    + "-threadName:" +Thread.currentThread().getName());
        }
    }

}

package com.java.tutorial.project.validate;

import lombok.Getter;

@Getter
public class HandleResult<R> {
    private final R data;

    private final boolean next;

    private HandleResult(R r, boolean next) {
        this.data = r;
        this.next = next;
    }

    public static <R> HandleResult<R> doNextResult() {
        return new HandleResult<>(null, true);
    }

    /**
     * 执行这个方法的handle将会停止执行接下来的方法 可以通过调整由县级的额方式放到最后
     *
     * @param r
     * @param <R>
     * @return
     */
    public static <R> HandleResult<R> doCurrentResult(R r) {
        return new HandleResult<>(r, false);
    }
}


package com.java.patterns.ChainofResponsibility;

public abstract class Handler<T> {

    protected Handler<T> next;

    private void next(Handler<T> next) {
        this.next = next;
    }

    /**
     * 执行方法
     *
     * @param user
     */
    public abstract void doHandler(User user);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler<T> handler) {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }
}

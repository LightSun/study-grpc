package com.bdfint.fgsb.terminal;

public interface Scheduler {

    void schedule(Runnable task);

    Scheduler DEFAULT = new Scheduler() {
        @Override
        public void schedule(Runnable task) {
            new Thread(task).start();
        }
    };
}

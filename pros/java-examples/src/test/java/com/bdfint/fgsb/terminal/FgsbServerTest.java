package com.bdfint.fgsb.terminal;

import java.io.IOException;

public final class FgsbServerTest {

    public static final int PORT = 50056;

    public static void main(String[] args) {
        ServerStreamManager manager = new ServerStreamManager(new Scheduler() {
            @Override
            public void schedule(Runnable task) {
                new Thread(task).start();
            }
        }, new ServerStreamManager.ReqProcessor() {
            @Override
            public ResMessage process(ServerStreamManager.UserSpace man, ReqMessage req) {
                System.out.println("server >>> receive client msg: " + req.toString());
                return ResMessage.newBuilder().setCode(req.getType()).setMsg("res success").build();
            }
        });
        new Thread(new Runnable() {
            int count;
            @Override
            public void run() {
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("--------- server start send message --------");
                while (count < 5){
                    System.out.println("server-send-message: " + count);
                    manager.addMessage(ResMessage.newBuilder().setCode(count).setMsg("server_msg__" + count).build());
                    count ++;
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                try {
//                    Thread.sleep(30000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("server: all msg is done.");
            }
        }).start();
        manager.setExceptionProcessor(new ServerStreamManager.ExceptionProcessor() {
            @Override
            public void process(ServerStreamManager.UserSpace us, Throwable e) {
            }
        });
        start(manager);
    }
    private static void start(ServerStreamManager manager){
        try {
            manager.start(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

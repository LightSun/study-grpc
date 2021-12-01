package com.bdfint.fgsb.terminal;

public class FgsbClientTest {

    public static void main(String[] args) {
        ClientStreamManager manager = new ClientStreamManager();
        manager.setResProcessor(new ClientStreamManager.ResProcessor() {
            @Override
            public ReqMessage process(ResMessage msg) {
                System.out.println("client >>> receive server msg: " + msg.toString());
                return null;
            }
        });

        new Thread(new Runnable() {
            int count;
            @Override
            public void run() {
                manager.addMessage(ReqMessage.newBuilder().setType(count).setData("client data__" + count).build());
//                while (count < 5){
//                    manager.addMessage(ReqMessage.newBuilder().setType(count).setData("client data__" + count).build());
//                    count ++;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("client: all msg is done.");
               // manager.end();
            }
        }).start();
        try {
            manager.start("localhost", FgsbServerTest.PORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

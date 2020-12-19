package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //new TestThread("+").start();
        //new TestThread("-").start();

        TestThread thread1 = new TestThread("+");
        thread1.start();
        TestThread thread2 = new TestThread("-");
        thread2.start();
        Thread.sleep(500);
        thread1.flag = false;
        thread1.join(); // ожидает завершение потока
        test("stopped!");
        // thread1.stop(); не завершает работу корректно


        //TestThread thread2 = new TestThread();
       // thread2.start();
        // Хотим получить: [+][-][+][-][+][-][+]
    }

    public static Object key = new Object();
    public static void test(String message) {
        try {
          //  synchronized (key) {
                System.out.print("[");
                Thread.sleep(200);
                System.out.print(message);
                Thread.sleep(200);
                System.out.print("]");
              //  key.notifyAll(); // возобновление потока у которого был wait
              //  key.wait(); // поток попадает в режим спячки (ожидания)
           // }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TestThread extends Thread {
    private String mess;
    public boolean flag = true;
    TestThread(String m) {
        this.mess = m;
    }
    @Override
    public void run() {
        // что-то долгое и тяжелое
        while(flag) {
            Main.test(this.mess);
        }
    }
}






/*
class TestThread222 implements Runnable {

    @Override
    public void run() {

    }
}
*/
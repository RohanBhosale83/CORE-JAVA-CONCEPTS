package org.example;

public class ThreadDemo {

    public static void main(String[] args) {
        SharedData data = new SharedData();

        Thread producer = new Producer(data);
        Thread consumer = new Consumer(data);
        producer.start();
        consumer.start();

    }
}

class Producer extends Thread {
    SharedData data;

    public Producer(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            data.produce(i);
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }

    }
}

class Consumer extends Thread {
    SharedData data;

    public Consumer(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Consumed value is: " + data.consume());
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){
                System.out.println(e);
            }
        }
    }
}

class SharedData {
    int data;
    private boolean writable = true;

    public synchronized void produce(int x) {
        if (writable != true)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        data = x;
        System.out.println("Data Produced: " + data);
        writable = false;
        notify();
    }

    public synchronized int consume() {
        if (writable)
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        writable=true;
        notify();
        return data;
    }

}

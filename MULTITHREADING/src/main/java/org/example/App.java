package org.example;

/**
 * Multithreading and Synchronization!
 */
public class App extends Thread {

    public static void main(String[] args) {
        System.out.println("Running Thread Demo !!!");

        App table = new App();

        Thread t1 = new Thread(table);
        t1.setName("T1");
        Thread t2 = new Thread(table);
        t2.setName("T2");
        t1.start();
        t2.start();

    }

    @Override
    public synchronized void run() {
        if (currentThread().getName().equalsIgnoreCase("T1")) {
            for (int i = 0; i < 10; i++) {
                if (i % 2 == 0) {
                    System.out.println("Running Thread " + currentThread().getName() + " for value :" + i);
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                if (i % 2 != 0) {
                    System.out.println("Running Thread " + currentThread().getName() + " for value :" + i);
                }
            }
        }
    }
}


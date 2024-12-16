package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class Counter {
  private int count = 0;

  // Synchronized method - remove synchronization, and you will get different values on different execution
  public synchronized void increment() {
    count++;
  }

  public synchronized int getCount() {
    return count;
  }
}

// this and above method is almost same but this is more optimised as it is applying on single code block
class CounterSynchronisedBlock {
  private int count = 0;
  private final Object lock = new Object();

  public void increment() {
    synchronized (lock) { // Synchronized block
      count++;
    }
  }

  public int getCount() {
    synchronized (lock) { // Synchronized block for reading value
      return count;
    }
  }
}



class SynchronizedMethodExample {
  public static void main(String[] args) throws InterruptedException {
    Counter counter = new Counter();

    // Create multiple threads to increment the counter
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        counter.increment();
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Counter value: " + counter.getCount());
  }
}

class SharedData {
  // this flag will be read by the threads from main memory only
  // if you remove the volatile keyword then the program will never finish because the second thread will read the value
  // from the cache/registers
  private volatile boolean flag = false;

  public void setFlag(boolean flag) {
    this.flag = flag;
  }

  public boolean isFlag() {
    return flag;
  }
}

class VolatileExample {
  public static void main(String[] args) {
    SharedData sharedData = new SharedData();

    Thread writer = new Thread(() -> {
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      sharedData.setFlag(true);
      System.out.println("Flag set to true");
    });

    Thread reader = new Thread(() -> {
      while (!sharedData.isFlag()) {
        // Busy waiting
      }
      System.out.println("Detected flag is true");
    });

    writer.start();
    reader.start();
  }
}


class AtomicVariableExample {
  private static final AtomicInteger count = new AtomicInteger(0);

  public static void main(String[] args) throws InterruptedException {
    // Create multiple threads to increment the atomic counter
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        count.incrementAndGet();
      }
    });

    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        count.incrementAndGet();
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

    System.out.println("Counter value: " + count.get());
  }
}
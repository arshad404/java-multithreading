package org.example;

class StartExample extends Thread {
  @Override
  public void run() {
    System.out.println("Thread is running");
  }

  public static void main(String[] args) {
    StartExample thread = new StartExample();
    thread.start();  // This will invoke the run() method in a new thread
  }
}


class RunExample extends Thread {
  @Override
  public void run() {
    System.out.println("Thread is running");
  }

  public static void main(String[] args) {
    RunExample thread = new RunExample();
    thread.start();  // Invokes run() in a separate call stack
  }
}

class SleepExample extends Thread {
  @Override
  public void run() {
    try {
      System.out.println("Thread started: " + Thread.currentThread().getName());
      Thread.sleep(5000);  // Sleep for 5 seconds
      System.out.println("Thread woke up after sleeping for 5 seconds");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    System.out.println("Main method started: " + Thread.currentThread().getName());
    SleepExample thread = new SleepExample();
    thread.start();
    System.out.println("Main method finished: " + Thread.currentThread().getName());
  }
}

class JoinExample extends Thread {
  @Override
  public void run() {
    for (int i = 0; i < 5; i++) {
      System.out.println(i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    JoinExample thread = new JoinExample();
    thread.start();

    try {
      thread.join();  // Main thread waits for this thread to finish
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Main thread resumes execution");
  }
}

//The purpose of the Thread.yield() method is to indicate that the current thread is willing to yield its current use of the CPU.
// This suggests to the thread scheduler that it can allocate CPU time to other threads.
class YieldExample {

  public static class YieldingThread extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + " - yield iteration: " + i);
        Thread.yield(); // Hint to scheduler that it can yield execution.
        // this run will not execute two time consecutively because of the yield method

      }
    }
  }

  public static class NonYieldingThread extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 5; i++) {
        System.out.println(Thread.currentThread().getName() + " - non-yield iteration: " + i);
      }
    }
  }

  public static void main(String[] args) {
    Thread yieldingThread = new YieldingThread();
    Thread nonYieldingThread = new NonYieldingThread();

    yieldingThread.setName("YieldingThread");
    nonYieldingThread.setName("NonYieldingThread");

    yieldingThread.start();
    nonYieldingThread.start();
  }
}



package org.example;

class RunningThread extends Thread {

  public static void main(String[] args) {
    RunningThread thread = new RunningThread();
    thread.start();
    System.out.println("This is outside of the thread");
  }

  public void run() {
    System.out.println("This is running inside thread which is in the main");
  }
}

class ImplementsThread implements Runnable {

  @Override
  public void run() {
    System.out.println("This is running in implements thread");
  }
}


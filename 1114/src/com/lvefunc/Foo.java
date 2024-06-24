package com.lvefunc;

class Foo {
	private boolean firstDone = false;
	private boolean secondDone = false;
	
    public Foo() {
    }

    public synchronized void first(Runnable printFirst) throws InterruptedException {
    	printFirst.run();
    	firstDone = true;
    	this.notifyAll();
    }

    public synchronized void second(Runnable printSecond) throws InterruptedException {
    	while (!firstDone)
    		wait();
    	printSecond.run();
    	secondDone = true;
    	this.notifyAll();
    }

    public synchronized void third(Runnable printThird) throws InterruptedException {
    	while (!secondDone)
    		wait();
    	printThird.run();
    }
}
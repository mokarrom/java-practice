/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mokarromh
 */
public class ThreadStatus { 
    private static final Logger LOGGER = Logger.getLogger(ThreadStatus.class.getName());
    
    private boolean suspended;
    private boolean stopped;
    
    private final String threadName;
    private final ReentrantLock lock;
    private final Condition condition;
    
    public ThreadStatus (String name) {
        threadName = name;
        lock = new ReentrantLock();
        condition = lock.newCondition();
        suspended = false;
        stopped = false;
    }
    
    // check for the thread to be paused
    public void checkForPause() {
        lock.lock();
        try {
            while (suspended) {                
                condition.await();
            }
        } catch (InterruptedException ie) {
            // interrupted
        } finally {
            lock.unlock();
        }
    }
    
    // Pause the thread
    public void suspend() {
        lock.lock();
        try {
            suspended = true;
        } finally {
            lock.unlock();
        }
        LOGGER.log(Level.FINE, "Thread: {0} Status : PAUSED", threadName);
    }
    
    // stop the thread completely
    public void stop() {
        lock.lock();
        try {
            stopped = true;
            suspended = false;
        } finally {
            lock.unlock();
        }
    }

    // Resume the thread
    public void resume() {
        lock.lock();
        try {
            suspended = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
        LOGGER.log(Level.FINE, "Thread: {0} Status : RESUMED", threadName);
    }
    
    public boolean isRunning() {
        return !(suspended || stopped);
    }

    public boolean isSuspended() {
        return suspended;
    }
    
    public boolean isStopped() {
        return stopped;
    }
    
    @Override
    public String toString() {
        return threadName;
    }
}

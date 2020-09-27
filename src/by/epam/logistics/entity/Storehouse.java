package by.epam.logistics.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Storehouse {
    private static Logger logger = LogManager.getLogger(Storehouse.class);
    private static Storehouse INSTANCE;

    private final Queue<Terminal> freeTerminals;
    private final Queue<Terminal> givenTerminals;
    private ReentrantLock locker;
    private Condition condition;

    private Storehouse(List<Terminal> terminals) {
        freeTerminals = new ArrayDeque<>();
        givenTerminals = new ArrayDeque<>();
        locker = new ReentrantLock();
        condition = locker.newCondition();
        freeTerminals.addAll(terminals);
    }

    public static Storehouse getInstance(List<Terminal> terminals) {
        if (INSTANCE == null) {
            INSTANCE = new Storehouse(terminals);
        }
        return INSTANCE;
    }

    public static Storehouse getInstance() {
        return INSTANCE;
    }

    public Terminal getTerminal() {
        Terminal terminal;
        try {
            locker.lock();
            while (freeTerminals.isEmpty()) {
                logger.warn("Terminals is occupied");
                condition.await();
            }
            terminal = freeTerminals.poll();
            givenTerminals.offer(terminal);
        } catch (InterruptedException e) {
            terminal = null;
            logger.warn("Thread was interrupted");
        } finally {
            locker.unlock();
        }
        return terminal;
    }

    public void releaseTerminal(Terminal terminal) {
        try {
            locker.lock();
            freeTerminals.offer(terminal);
            givenTerminals.remove();
        } finally {
            condition.signal();
            locker.unlock();
        }
    }

}
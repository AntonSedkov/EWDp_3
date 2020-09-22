package by.epam.logistics.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Storehouse {

    private static Logger logger = LogManager.getLogger(Storehouse.class);
    private static final Storehouse INSTANCE = new Storehouse();

    private static final int TERMINAL_QUANTITY = 3;
    private ReentrantLock locker;
    private final Queue<Terminal> givenTerminals;
    private final Queue<Terminal> freeTerminals;

    private Storehouse() {
        locker = new ReentrantLock();
        freeTerminals = new ArrayDeque<>();
        givenTerminals = new ArrayDeque<>();
        for (int i = 0; i < TERMINAL_QUANTITY; i++) {
            Terminal terminal = Terminal.getTerminal();
            terminal.setIdTerminal(i);
            freeTerminals.add(terminal);
        }
        if (freeTerminals.size() == 0) {
            throw new RuntimeException("Storehouse is unable to create Terminals");
        }
    }

    public Terminal getTerminal() {
        Terminal terminal = null;
        try {
            locker.lock();
            if (!freeTerminals.isEmpty()) {
                terminal = freeTerminals.poll();
                givenTerminals.offer(terminal);
            } else {
                locker.newCondition().await();
                logger.warn("Terminals is occupied");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }
        return terminal;
    }

    public void releaseTerminal(Terminal terminal) {
        locker.lock();

        try {
            if (givenTerminals.remove(terminal)) {
                freeTerminals.offer(terminal);
            }
        } finally {
            locker.unlock();
            locker.newCondition().signalAll();
        }


    }

    public static Storehouse getInstance() {
        return INSTANCE;
    }

}
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
        locker.lock();
        if (!freeTerminals.isEmpty() || givenTerminals.size() == TERMINAL_QUANTITY) {
            terminal = freeTerminals.poll();
            givenTerminals.offer(terminal);
        } else {
            logger.warn("Terminals is occupied");
        }
        locker.unlock();
        return terminal;
    }

    public void releaseTerminal(Terminal terminal) {
        locker.lock();
        if (givenTerminals.remove(terminal)) {
            freeTerminals.offer(terminal);
        }
    }

    public static Storehouse getInstance() {
        return INSTANCE;
    }

}
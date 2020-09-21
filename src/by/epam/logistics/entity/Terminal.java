package by.epam.logistics.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terminal {
    private static Logger logger = LogManager.getLogger(Terminal.class);
    public static final int MAX_CAPACITY = 500;
    private int emptyCapacity;
    private int idTerminal;

    private Terminal(int emptyCapacity) {
        this.emptyCapacity = emptyCapacity;
    }

    public int getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(int idTerminal) {
        this.idTerminal = idTerminal;
    }

    public void setEmptyCapacity(int emptyCapacity) {
        this.emptyCapacity = emptyCapacity;
    }

    public int getEmptyCapacity() {
        return emptyCapacity;
    }

    static Terminal getTerminal() {
        Terminal terminal = new Terminal(MAX_CAPACITY);
        logger.info("Terminal is created");
        return terminal;
    }

}
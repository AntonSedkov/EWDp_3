package by.epam.logistics.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class Terminal {
    public static final int MAX_CAPACITY = 500;
    private static Logger logger = LogManager.getLogger(Terminal.class);

    private int idTerminal;
    private AtomicInteger emptyCapacity;

    public Terminal(int idTerminal, int emptyCapacity) {
        this.idTerminal = idTerminal;
        this.emptyCapacity = new AtomicInteger(emptyCapacity);
    }

    public int getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(int idTerminal) {
        this.idTerminal = idTerminal;
    }

    public AtomicInteger getEmptyCapacity() {
        return emptyCapacity;
    }

    public void setEmptyCapacity(int emptyCapacity) {
        this.emptyCapacity = new AtomicInteger(emptyCapacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Terminal terminal = (Terminal) o;
        if (idTerminal != terminal.idTerminal) return false;
        return emptyCapacity != null ? emptyCapacity.equals(terminal.emptyCapacity) : terminal.emptyCapacity == null;
    }

    @Override
    public int hashCode() {
        int result = idTerminal;
        result = 31 * result + (emptyCapacity != null ? emptyCapacity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Terminal.class.getSimpleName() + "[", "]")
                .add("idTerminal=" + idTerminal)
                .add("emptyCapacity=" + emptyCapacity)
                .toString();
    }

}
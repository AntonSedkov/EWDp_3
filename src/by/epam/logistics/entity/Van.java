package by.epam.logistics.entity;

import by.epam.logistics.state.BaseState;
import by.epam.logistics.state.EnterState;
import by.epam.logistics.state.ExitState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

public class Van implements Runnable {
    public static final int MAX_CAPACITY = 250;
    private static Logger logger = LogManager.getLogger(Van.class);

    private int vanId;
    private int cargoCapacity;
    private CargoType cargoType;
    private Terminal terminal;
    private BaseState state;

    public Van(int vanId, int cargoCapacity, CargoType cargoType) {
        this.vanId = vanId;
        this.cargoCapacity = cargoCapacity;
        this.cargoType = cargoType;
        state = new EnterState();
    }

    public BaseState getState() {
        return state;
    }

    public void setState(BaseState state) {
        this.state = state;
    }

    public int getVanId() {
        return vanId;
    }

    public void setVanId(int vanId) {
        this.vanId = vanId;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    public CargoType getCargoType() {
        return cargoType;
    }

    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    @Override
    public void run() {
        if (cargoType == CargoType.PERISHABLE) {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
        boolean flag = true;
        while (flag) {
            if (state.getClass().equals(ExitState.class)) {
                flag = false;
            }
            logger.info("Current state: " + state.getClass() + ". Current van: " + vanId
                    + ". Current terminal: " + ((terminal != null) ? terminal.getIdTerminal() : "no"));
            state.activateState(this);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Van van = (Van) o;
        if (vanId != van.vanId) return false;
        if (cargoCapacity != van.cargoCapacity) return false;
        if (cargoType != van.cargoType) return false;
        if (terminal != null ? !terminal.equals(van.terminal) : van.terminal != null) return false;
        return state != null ? state.equals(van.state) : van.state == null;
    }

    @Override
    public int hashCode() {
        int result = vanId;
        result = 31 * result + cargoCapacity;
        result = 31 * result + (cargoType != null ? cargoType.hashCode() : 0);
        result = 31 * result + (terminal != null ? terminal.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Van.class.getSimpleName() + "[", "]")
                .add("vanId=" + vanId)
                .add("cargoCapacity=" + cargoCapacity)
                .add("cargoType=" + cargoType)
                .add("terminal=" + terminal)
                .add("state=" + state)
                .toString();
    }

}
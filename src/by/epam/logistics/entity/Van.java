package by.epam.logistics.entity;

import by.epam.logistics.state.BaseState;
import by.epam.logistics.state.EnterState;
import by.epam.logistics.state.ExitState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class Van implements Runnable {
    public enum CargoType {
        USUAL, PERISHABLE
    }

    private static Logger logger = LogManager.getLogger(Van.class);
    private BaseState state;

    public static final int MAX_CAPACITY = 50;
    private int vanId;
    private int cargoCapacity;
    private Terminal terminal;
    private CargoType cargoType;

    public Van(int vanId, CargoType cargoType) {
        this.vanId = vanId;
        this.cargoCapacity = MAX_CAPACITY;
        this.cargoType = cargoType;
        state = new EnterState();
    }

    public BaseState getState() {
        return state;
    }

    public void setState(BaseState state) {
        this.state = state;
    }

    public void activateNextState() {
        state.activate(this);
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
        while (!state.getClass().equals(ExitState.class)) {
            logger.info("Current state: " + state.getClass() + ". Current van: " + vanId
                    + ". Current terminal: " + ((terminal != null) ? terminal.getIdTerminal() : "no"));
            state.activate(this);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
    }

}
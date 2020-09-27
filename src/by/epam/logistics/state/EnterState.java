package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnterState implements BaseState {
    private static Logger logger = LogManager.getLogger(EnterState.class);

    @Override
    public void activateState(Van van) {
        logger.info("Van" + van.getVanId() + " has entered into the storehouse.");
        int cargo = van.getCargoCapacity();
        BaseState nextState = (cargo == 0) ? new LoadCargoState() : new UnloadCargoState();
        Terminal terminal = null;
        while (terminal == null) {
            while (terminal == null) {
                terminal = Storehouse.getInstance().getTerminal();
            }
            if (nextState.getClass().equals(LoadCargoState.class)) {
                terminal = (terminal.getEmptyCapacity().get() < Terminal.MAX_CAPACITY) ? terminal : null;
            } else {
                terminal = (terminal.getEmptyCapacity().get() > 0) ? terminal : null;
            }
        }
        van.setTerminal(terminal);
        van.setState(nextState);
        logger.info("Change state to next");
    }

}
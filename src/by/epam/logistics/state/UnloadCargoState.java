package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnloadCargoState implements BaseState {
    private static Logger logger = LogManager.getLogger(UnloadCargoState.class);

    @Override
    public void activateState(Van van) {
        int cargo = van.getCargoCapacity();
        Terminal terminal = van.getTerminal();
        int terminalCapacity = terminal.getEmptyCapacity().get();
        int difference;
        if (terminalCapacity >= cargo) {
            difference = terminalCapacity - cargo;
            terminal.setEmptyCapacity(difference);
            Storehouse.getInstance().releaseTerminal(terminal);
            van.setCargoCapacity(0);
            van.setTerminal(null);
            van.setState(new ExitState());
        } else {
            difference = cargo - terminalCapacity;
            terminal.setEmptyCapacity(0);
            Storehouse.getInstance().releaseTerminal(terminal);
            van.setCargoCapacity(difference);
            van.setState(new EnterState());
        }
        logger.info("Change state to next");
    }

}
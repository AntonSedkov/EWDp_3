package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoadCargoState implements BaseState {
    private static Logger logger = LogManager.getLogger(LoadCargoState.class);

    @Override
    public void activateState(Van van) {
        int vanEmptyCapacity = Van.MAX_CAPACITY;
        Terminal terminal = van.getTerminal();
        int terminalEmptyCapacity = terminal.getEmptyCapacity().get();
        int terminalProduction = Terminal.MAX_CAPACITY - terminal.getEmptyCapacity().get();
        if (vanEmptyCapacity >= terminalProduction) {
            terminal.setEmptyCapacity(Terminal.MAX_CAPACITY);
        } else {
            terminal.setEmptyCapacity(terminalEmptyCapacity + vanEmptyCapacity);
        }
        van.setTerminal(null);
        Storehouse.getInstance().releaseTerminal(terminal);
        van.setState(new ExitState());
        logger.info("Change state to next");
    }

}
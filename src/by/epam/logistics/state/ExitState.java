package by.epam.logistics.state;

import by.epam.logistics.entity.Van;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExitState implements BaseState {
    private static Logger logger = LogManager.getLogger(ExitState.class);

    @Override
    public void activateState(Van van) {
        logger.info("Van" + van.getVanId() + " has left the storehouse.");
    }

}
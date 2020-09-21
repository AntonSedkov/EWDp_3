package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;

public class EnterState implements BaseState {

    @Override
    public void activate(Van van) {
        int cargo = van.getCargoCapacity();
        BaseState nextState = (cargo == 0) ? new LoadCargoState() : new UnloadCargoState();
        Terminal terminal = null;
        while (terminal == null) {
            while (terminal == null) {
                terminal = Storehouse.getInstance().getTerminal();
            }
            if (nextState.getClass().equals(LoadCargoState.class)) {
                terminal = (terminal.getEmptyCapacity() < Terminal.MAX_CAPACITY) ? terminal : null;
            } else {
                terminal = (terminal.getEmptyCapacity() > 0) ? terminal : null;
            }
        }
        van.setTerminal(terminal);
        van.setState(nextState);
    }

}
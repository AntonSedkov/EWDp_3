package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;

public class UnloadCargoState implements BaseState {

    @Override
    public void activate(Van van) {
        int cargo = van.getCargoCapacity();
        Terminal terminal = van.getTerminal();
        int terminalCapacity = terminal.getEmptyCapacity();
        int difference;
        if (terminalCapacity >= cargo) {
            difference = terminalCapacity - cargo;
            terminal.setEmptyCapacity(difference);
            van.setCargoCapacity(0);
            van.setTerminal(null);
            Storehouse.getInstance().releaseTerminal(terminal);
            van.setState(new ExitState());
        } else {
            difference = cargo - terminalCapacity;
            van.setCargoCapacity(difference);
            terminal.setEmptyCapacity(0);
            Storehouse.getInstance().releaseTerminal(terminal);
            van.setState(new EnterState());
        }
    }

}
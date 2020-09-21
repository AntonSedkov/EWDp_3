package by.epam.logistics.state;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;

public class LoadCargoState implements BaseState {

    @Override
    public void activate(Van van) {
        int vanEmptyCapacity = Van.MAX_CAPACITY;
        Terminal terminal = van.getTerminal();
        int terminalEmptyCapacity = terminal.getEmptyCapacity();
        int terminalProduction = Terminal.MAX_CAPACITY - terminal.getEmptyCapacity();
        if (vanEmptyCapacity >= terminalProduction) {
            terminal.setEmptyCapacity(Terminal.MAX_CAPACITY);
        } else {
            terminal.setEmptyCapacity(terminalEmptyCapacity + vanEmptyCapacity);
        }
        van.setTerminal(null);
        Storehouse.getInstance().releaseTerminal(terminal);
        van.setState(new ExitState());
    }

}
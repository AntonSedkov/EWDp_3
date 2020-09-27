package by.epam.logistics.factory;

import by.epam.logistics.entity.Terminal;
import by.epam.logistics.validators.ProjectDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TerminalFactory {
    private static Logger logger = LogManager.getLogger(TerminalFactory.class);
    private static final int QUANTITY_PARAMETERS = 2;

    public Terminal createInstance(List<String> lines) {
        Terminal terminal = null;
        if (lines != null && lines.size() == QUANTITY_PARAMETERS) {
            if (ProjectDataValidator.isId(lines.get(0)) &&
                    ProjectDataValidator.isCorrectCapacity(lines.get(1))) {
                terminal = new Terminal(Integer.parseInt(lines.get(0)), Integer.parseInt(lines.get(1)));
                logger.info("Terminal" + terminal.getIdTerminal() + " has been created.");
            }
        }
        return terminal;
    }

}
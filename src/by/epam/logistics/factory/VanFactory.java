package by.epam.logistics.factory;

import by.epam.logistics.entity.CargoType;
import by.epam.logistics.entity.Van;
import by.epam.logistics.validators.ProjectDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class VanFactory {
    private static Logger logger = LogManager.getLogger(VanFactory.class);
    private static final int QUANTITY_PARAMETERS = 3;

    public Van createInstance(List<String> lines) {
        Van van = null;
        if (lines != null && lines.size() == QUANTITY_PARAMETERS) {
            if (ProjectDataValidator.isId(lines.get(0)) &&
                    ProjectDataValidator.isCorrectCargo(lines.get(1))) {
                van = new Van(Integer.parseInt(lines.get(0)), Integer.parseInt(lines.get(1)), CargoType.valueOf(lines.get(2).toUpperCase()));
                logger.info("Van" + van.getVanId() + " has been created.");
            }
        }
        return van;
    }

}
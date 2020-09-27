package by.epam.logistics.validators;

import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;

public class ProjectDataValidator {

    private ProjectDataValidator() {
    }

    public static boolean isId(String id) {
        int idNum = Integer.parseInt(id);
        return idNum > 0;
    }

    public static boolean isCorrectCargo(String cargo) {
        int cargoInt = Integer.parseInt(cargo);
        return cargoInt >= 0 && cargoInt <= Van.MAX_CAPACITY;
    }

    public static boolean isCorrectCapacity(String capacity) {
        int capacityInt = Integer.parseInt(capacity);
        return capacityInt >= 0 && capacityInt <= Terminal.MAX_CAPACITY;
    }

}
package by.epam.logistics;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Van;

public class Main {

    public static void main(String[] args) {

        Storehouse storehouse = Storehouse.getInstance();

        Van van1 = new Van(1, Van.CargoType.USUAL);
        Van van2 = new Van(2, Van.CargoType.USUAL);
        Van van3 = new Van(3, Van.CargoType.PERISHABLE);
        Van van4 = new Van(4, Van.CargoType.USUAL);
        Van van5 = new Van(5, Van.CargoType.PERISHABLE);
        Van van6 = new Van(6, Van.CargoType.USUAL);
        Van van7 = new Van(7, Van.CargoType.PERISHABLE);
        Van van8 = new Van(8, Van.CargoType.USUAL);
        Van van9 = new Van(9, Van.CargoType.USUAL);

        Thread thread1 = new Thread(van1, "van1");
        Thread thread2 = new Thread(van2, "van2");
        Thread thread3 = new Thread(van3, "van3");
        Thread thread4 = new Thread(van4, "van4");
        Thread thread5 = new Thread(van5, "van5");
        Thread thread6 = new Thread(van6, "van6");
        Thread thread7 = new Thread(van7, "van7");
        Thread thread8 = new Thread(van8, "van8");
        Thread thread9 = new Thread(van9, "van9");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();

    }

}
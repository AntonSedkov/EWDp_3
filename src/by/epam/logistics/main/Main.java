package by.epam.logistics.main;

import by.epam.logistics.entity.Storehouse;
import by.epam.logistics.entity.Terminal;
import by.epam.logistics.entity.Van;
import by.epam.logistics.factory.TerminalFactory;
import by.epam.logistics.factory.VanFactory;
import by.epam.logistics.parser.VanParser;
import by.epam.logistics.reader.ProjectReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            List<String> linesTerminal = ProjectReader.readFileData("data\\terminals.txt");
            List<List<String>> parsedLinesTerminal = VanParser.parseVans(linesTerminal);
            TerminalFactory factoryTerminal = new TerminalFactory();
            List<Terminal> terminals = new ArrayList<>();
            for (List<String> line : parsedLinesTerminal) {
                Terminal terminal = factoryTerminal.createInstance(line);
                terminals.add(terminal);
            }
            Storehouse storehouse = Storehouse.getInstance(terminals);

            List<String> linesVan = ProjectReader.readFileData("data\\vans.txt");
            List<List<String>> parsedLinesVan = VanParser.parseVans(linesVan);
            VanFactory factoryVan = new VanFactory();
            List<Van> vans = new ArrayList<>();
            for (List<String> line : parsedLinesVan) {
                Van van = factoryVan.createInstance(line);
                vans.add(van);
            }

            List<Thread> threads = new ArrayList<>();
            for (Van van : vans) {
                Thread thread = new Thread(van, "van" + van.getVanId());
                threads.add(thread);
            }
            for (Thread thread : threads) {
                thread.start();
            }

        } catch (Exception e) {
            logger.info("Something goes wrong..." + e);
        }

    }
}
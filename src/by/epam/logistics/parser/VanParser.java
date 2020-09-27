package by.epam.logistics.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VanParser {
    private static Logger logger = LogManager.getLogger(VanParser.class);
    private static final String SPLITTER = "\\s+";

    private VanParser() {
    }

    public static List<List<String>> parseVans(List<String> data) {
        List<List<String>> lines = new ArrayList<>();
        for (String line : data) {
            String[] params = line.split(SPLITTER);
            List<String> characteristics = new ArrayList<>(Arrays.asList(params));
            lines.add(characteristics);
        }
        logger.info("Data has been successfully parsed.");
        return lines;
    }

}
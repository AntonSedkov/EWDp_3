package by.epam.logistics.reader;

import by.epam.logistics.exception.ProjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProjectReader {
    private static Logger logger = LogManager.getLogger(ProjectReader.class);

    private ProjectReader() {
    }

    public static List<String> readFileData(String filepath) throws ProjectException {
        try (Stream<String> lineStream = Files.lines(Paths.get(filepath))) {
            List<String> lines = lineStream.collect(Collectors.toList());
            logger.info("Lines from file has been read successfully.");
            return lines;
        } catch (IOException e) {
            throw new ProjectException("File has not been read. Wrong path: " + filepath, e);
        }
    }

}
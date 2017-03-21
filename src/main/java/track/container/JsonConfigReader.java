package track.container;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import track.container.config.*;

/**
 * TODO: Реализовать
 */
public class JsonConfigReader implements ConfigReader {

    @Override
    public List<Bean> parseBeans(File configFile) throws InvalidConfigurationException {
        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        System.out.println(configFile.getAbsolutePath());
        try {
            Root root = objectMapper.readValue(configFile, Root.class);
            System.out.println("Beans Objects\n" + root.getBeans().toString());
            return root.getBeans();

        } catch (IOException e) {
            throw new InvalidConfigurationException(e.getMessage());
        }

    }
}

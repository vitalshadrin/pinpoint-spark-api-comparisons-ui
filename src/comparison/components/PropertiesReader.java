package comparison.components;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private final String PROPERTY_PATH = "src/comparison/resources/appText.properties";

     public String getProperty(String key) {
        FileInputStream fileInputStream;
        Properties property = new Properties();
        try {
            fileInputStream = new FileInputStream(PROPERTY_PATH);
            property.load(fileInputStream);
            return property.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}


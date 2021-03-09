package comparison.app.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;

    public PropertiesReader(PropertyPath propertyPath) {
        properties = getProperty(propertyPath.getPropertyPath());
    }

    private Properties getProperty(String propertyName) {
        FileInputStream fileInputStream;
        Properties property = new Properties();
        try {
            fileInputStream = new FileInputStream(propertyName);
            property.load(fileInputStream);
            return property;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Properties getProperties() {
        return properties;
    }
}


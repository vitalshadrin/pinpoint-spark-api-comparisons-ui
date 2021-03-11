package comparison.app.property;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private Properties properties;
    private PropertyPath propertyPath;

    public PropertiesReader(PropertyPath propertyPath) {
        this.propertyPath = propertyPath;
        this.properties = getProperty(propertyPath.getPropertyPath());
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

    public void saveProperty(String key, String value) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(this.propertyPath.getPropertyPath());
            this.properties.setProperty(key, value);
            this.properties.store(out, null);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Properties getProperties() {
        return properties;
    }
}


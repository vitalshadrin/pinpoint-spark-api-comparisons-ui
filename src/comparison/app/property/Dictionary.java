package comparison.app.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Dictionary {

    public String getAppText(String key) {
        Properties property = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("appText.properties")) {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(key);
    }
}

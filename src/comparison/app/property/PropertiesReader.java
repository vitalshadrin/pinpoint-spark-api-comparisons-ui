package comparison.app.property;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesReader {
    private final Properties properties;
    private PropertyPath propertyPath;

    public PropertiesReader(PropertyPath propertyPath) {
        this.propertyPath = propertyPath;
        this.properties = getProperty(propertyPath.getPropertyPath());
    }

    public PropertiesReader(String propertyPath) {
        this.properties = getProperty(propertyPath);
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

    public void saveProperty(Map<String, String> properties) {
        properties.forEach((key, value) -> {
            try {
                // input the file content to the StringBuffer "input"
                BufferedReader file = new BufferedReader(new FileReader(this.propertyPath.getPropertyPath()));
                StringBuffer inputBuffer = new StringBuffer();
                String line;
                //read file and in StringBuffer
                while ((line = file.readLine()) != null) {
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                file.close();
                // replace data
                List<String> inputStr = Arrays.asList(inputBuffer.toString().split("\n"));
                String saveData = inputStr.stream().map(data -> (data.startsWith(key + "=") && !data.startsWith("#")) ? key + "=" + value : data).collect(Collectors.joining("\n"));
                // write the new string with the replaced line OVER the same file
                FileOutputStream fileOut = new FileOutputStream(this.propertyPath.getPropertyPath());
                fileOut.write(saveData.getBytes());
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Properties getProperties() {
        return properties;
    }
}


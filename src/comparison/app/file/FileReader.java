package comparison.app.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readFile(FilePath file) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(file.getFilePath())), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

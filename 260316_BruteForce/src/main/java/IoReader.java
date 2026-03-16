
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IoReader {

    public static List<Person> getPersonsFromFile(String fileName){
        List<Person> persons = new ArrayList<>();

        Path path = Paths.get(
                System.getProperty("user.dir"),
                "src",
                "main",
                "resources",
                fileName
        );


        try {
            List<String> lines = Files.readAllLines(path);

            persons = lines.stream()
                    .map(l -> new Person(l))
                    .toList();

        } catch (IOException e) {

            throw new RuntimeException("Failed to read the File: " + fileName);
        }

        return persons;
    }
}

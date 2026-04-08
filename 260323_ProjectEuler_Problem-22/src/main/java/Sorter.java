import java.util.ArrayList;
import java.util.stream.Collectors;

public class Sorter {
    public ArrayList<String> sort(ArrayList<String> list) {

        list = list.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toCollection(ArrayList::new));

        return  list;
    }
}

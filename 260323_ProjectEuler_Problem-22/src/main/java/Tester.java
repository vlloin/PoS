import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Tester {
    protected static ArrayList<String> listOfNames;
    protected static HashMap<Integer, Integer> worth;

    public static void main(String[] args) {
        listOfNames = new ArrayList<>();
        worth = new HashMap<>();

        Path path = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "names.txt");
        try{
            for (String line : Files.readAllLines(path)) {
                line = line.replace("\"", "");
                String[] parts = line.split(",");
                listOfNames.addAll(Arrays.asList(parts));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Sorter sorter = new Sorter();
        listOfNames = sorter.sort(listOfNames);

        // listOfNames.forEach(System.out::println);

        int THREAD_COUNT = 1;
        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < THREAD_COUNT; i++){
            Thread thread = new Thread(new OwnRunnable((1000/THREAD_COUNT) * i, (1000/THREAD_COUNT) * i + 1000/THREAD_COUNT));
            threads.add(thread);
        }

        for(Thread thread : threads){
            thread.start();
        }

        for(Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int result = 0;
        for (Integer value : worth.values()) {
            // System.out.println(value);
            result += value;
        }

        System.out.println(result);

    }

    public static class OwnRunnable implements Runnable{

        private final int lowerRange;
        private final int upperRange;
        private final Calculator calc;

        public OwnRunnable(int lowerRange, int upperRange){
            this.lowerRange = lowerRange;
            this.upperRange = upperRange;
            calc = new Calculator();
        }


        @Override
        public void run() {
            for(int i = lowerRange; i < upperRange; i++){
                worth.put(i, (i+1) * calc.calculateWorth(listOfNames.get(i)));
                System.out.println(": " + i + ": " + listOfNames.get(i) + ": " + worth.get(i));
            }
        }
    }

}

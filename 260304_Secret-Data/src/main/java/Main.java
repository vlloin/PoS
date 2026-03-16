import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    static HashMap<Integer, String> passwords = new HashMap<>();

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Path path = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "secret-data.csv");
        File file = path.toFile();
        try{
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",");
                Person person = new Person();
                person.firstName = parts[0];
                person.lastName = parts[1];
                person.hash = parts[2];
                people.add(person);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        int numberOfThreads = 16;
        int totalPeople = people.size();

        int blockSize = totalPeople / numberOfThreads;
        int rest = totalPeople % numberOfThreads;

        List<Thread> threads = new ArrayList<>();

        int startIndex = 0;

        for (int i = 0; i < numberOfThreads; i++) {

            int endIndex = startIndex + blockSize;

            // Rest auf die ersten Threads verteilen
            if (i < rest) {
                endIndex++;
            }

            List<Person> subList = people.subList(startIndex, endIndex);

            Thread thread = new Thread(new OwnRunnable(subList, i*blockSize, passwords));
            threads.add(thread);

            startIndex = endIndex;
        }

        long startTime = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (startTime-endTime));

        passwords.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });

    }

    public static class OwnRunnable implements Runnable {

        List<Person> people = new ArrayList<>();
        int startIndex = 0;
        HashMap<Integer, String> passwords;

        public OwnRunnable(List<Person> people, int index, HashMap<Integer, String> passwords) {
            this.people = people;
            this.startIndex = index;
            this.passwords = passwords;
        }

        @Override
        public void run() {
            Hasher hasher = new Hasher();

            for (Person person : people) {

                long startTime = System.currentTimeMillis();
                boolean found = false;

                for (char a = 'a'; a <= 'z' && !found; a++) {
                    for (char b = 'a'; b <= 'z' && !found; b++) {
                        for (char c = 'a'; c <= 'z' && !found; c++) {
                            for (char d = 'a'; d <= 'z' && !found; d++) {
                                for (char e = 'a'; e <= 'z' && !found; e++) {

                                    String password = "" + a + b + c + d + e;
                                    String combined = person.firstName + person.lastName + password;

                                    try {
                                        String hash = hasher.toMd5(combined);

                                        if (hash.equals(person.hash)) {
                                            long endTime = System.currentTimeMillis();
                                            // System.out.println("Passwort gefunden für " + person.firstName + " " + person.lastName + ": " + password);

                                            // System.out.println("Zeit: " + (endTime - startTime) + " ms");

                                            passwords.put(startIndex, password);
                                            startIndex++;

                                            found = true;
                                        }

                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

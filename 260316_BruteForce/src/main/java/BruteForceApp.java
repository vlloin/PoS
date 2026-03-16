import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BruteForceApp {


    public static void main(String[] args) {

        BruteForceApp app = new BruteForceApp();

        List<Person> persons = IoReader.getPersonsFromFile("secret-data.csv");
        System.out.println("person: size: " + persons.size());

        Long startTime = System.currentTimeMillis();
/*
        // process
        app.runAsProcess(persons);

*/
        // threads
        // app.runAsThreadArray(persons);


        app.runAsExecutorService(persons);
        System.out.println(System.currentTimeMillis()-startTime + " ms");
        System.out.println("Person " + persons.get(1));
        System.out.println("Person " + persons.get(99));

    }

    public void runAsProcess(List<Person> persons){
        for(int i = 0; i < persons.size(); i++) {
            Runnable runnable = new Worker(persons.get(i));
            runnable.run();
            System.out.println("Person " + persons.get(i));
        }
    }

    public void runAsThreadArray(List<Person> persons){
        final int THREAD_SIZE = 8;
        Thread[] thread = new Thread[THREAD_SIZE];
        for(int i = 0; i < persons.size() / THREAD_SIZE; i+=THREAD_SIZE){
        //for(int i = 0; i < 100 / THREAD_SIZE; i+=5){
            for(int j = 0;j < THREAD_SIZE; j++) {
                Runnable runnable = new Worker(persons.get(i+j));
                thread[j] = new Thread(runnable);
            }

            for(int j = 0;j < THREAD_SIZE; j++) {
                thread[j].start();
            }

            try{
                for(int j = 0;j < THREAD_SIZE; j++) {
                    thread[j].join();
                }
            } catch (InterruptedException iex){
                System.out.println("interrupped");
            }
        }
    }

    public void runAsExecutorService(List<Person> persons){
        final int THREAD_SIZE = 10;
        ExecutorService executorService = Executors
    // 1.           .newFixedThreadPool(THREAD_SIZE); // Generate a pool of 10 Threads
                    .newCachedThreadPool();

        for(Person p : persons){
    // 1.   executorService.execute(new Worker(p));
            executorService.submit(new Worker(p));
        }

        // verhindert das weitere aufnehmen von Threads
        executorService.shutdown();

        try{
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        }catch (InterruptedException iex){
            // System.out.println("interrupped");
            iex.printStackTrace();
        }
    }
}

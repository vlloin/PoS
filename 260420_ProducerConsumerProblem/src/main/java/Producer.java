import java.util.Random;

public class Producer implements Runnable {
    private final Stack stack;

    public Producer(Stack stack){
        this.stack = stack;
    }

    @Override
    public void run() {
        for(;;){
            stack.push(new Random().nextInt(0, 100));
            System.out.println("Produced...");;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(this.getClass().getSimpleName() + ": Producer interrupted");
            }
        }
    }
}

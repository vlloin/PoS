public class Producer implements Runnable {
    private final Stack stack;
    private int debugCounter = 0;

    public Producer(Stack stack){
        this.stack = stack;
    }

    @Override
    public void run() {
        for(;;){
            synchronized (stack) {
                if(stack.isFull()){
                    int value = debugCounter++;
                    stack.push(value);
                    // stack.push(new Random().nextInt(0, 100));
                    System.out.println(Thread.currentThread().getName() + " Produced..." +  value);;
                    stack.notifyAll();
                }
            }
            try {
                System.out.println(Thread.currentThread().getName() + " stack " + stack.toString());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(this.getClass().getSimpleName() + ": Producer interrupted");
            }
        }
    }
}

public class Consumer implements Runnable {
    private Stack stack;

    public Consumer(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        for(;;){
            synchronized (stack) {
                if(stack.isEmpty()){
                    int value = stack.pop();
                    System.out.println("Consumed: " + value);
                }
            }
            try {
                System.out.println(Thread.currentThread().getName() + " stack " + stack.toString());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(this.getClass().getSimpleName() + ": Consumer interrupted");
            }
        }
    }
}

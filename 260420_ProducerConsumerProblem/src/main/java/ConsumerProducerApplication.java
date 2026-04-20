public class ConsumerProducerApplication {
    public static void main(String[] args) {
        Stack stack = new Stack(10);

        // Consumer & Producer which will have the stack as instance variable
        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        Thread prod = new  Thread(producer);
        Thread consum = new  Thread(consumer);

        prod.start();
        consum.start();

        System.out.println("Cya...");
    }
}

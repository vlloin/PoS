public class ConsumerProducerApplication {
    public static void main(String[] args) {
        Stack stack = new Stack(10);

        // Consumer & Producer which will have the stack as instance variable
        int producerAmount = 2;
        int consumerAmount = 2;

        Producer[] producers = new Producer[producerAmount];

        for(int i = 0; i < producerAmount; i++){
            producers[i] = new Producer(stack);
        }

        Consumer[] consumers = new Consumer[consumerAmount];

        for(int i = 0; i < consumerAmount; i++){
            consumers[i] = new Consumer(stack);
        }

        Thread[] producerThreads = new Thread[producerAmount];
        for(int i = 0; i < producerAmount; i++){
            producerThreads[i] = new Thread(producers[i]);
            producerThreads[i].setName("Producer " + i);
        }

        Thread[] consumerThreads = new Thread[consumerAmount];
        for(int i = 0; i < consumerAmount; i++){
            consumerThreads[i] = new Thread(consumers[i]);
            consumerThreads[i].setName("ConsumerThread " + i);
        }

        for(int i = 0; i < producerAmount; i++){
            producerThreads[i].start();
        }

        for(int i = 0; i < consumerAmount; i++){
            consumerThreads[i].start();
        }

        System.out.println("Cya...");
    }
}

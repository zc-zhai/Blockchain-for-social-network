package ProducerAndConsumer.Version2;

import java.util.List;
import java.util.Random;

/*
Producer Function
 */
public class Producer implements Runnable {
    private List<PCData> queue;
    private int length;

    public Producer(List<PCData> queue, int length) {
        this.queue = queue;
        this.length = length;
    }

    @Override
    public void run()
    {
        try {
            while (true) {
                // lock the queue
                synchronized (queue) {
                    // consistently check if queue is full
                    // if queue is full, let the producer thread wait
                    while (queue.size() == length) {
                        try {
                            System.out.println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt(100);    // create a random int from 0 to 100
                    PCData data = new PCData();
                    data.set(i);
                    queue.add(data);
                    System.out.println(" User " + Thread.currentThread().getId() + " Produces value : " + i);
                    System.out.println(" Queue size: " + queue.size());
                    queue.notifyAll();  // notify other threads
                }
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
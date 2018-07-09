package ProducerAndConsumer.Version2;

import java.util.List;

/*
Consumer Function
 */

public class Consumer implements Runnable {
    private List<PCData> queue;

    public Consumer(List<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // lock the queue
                synchronized (queue) {
                    // consistently check if queue is empty
                    // if queue is empty, let the consumer thread wait
                    while (queue.isEmpty()) {

                        try {
                            System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    PCData data = queue.remove(0);
                    System.out.println(" User " + Thread.currentThread().getId() + " Consumes value : " + data.get());
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
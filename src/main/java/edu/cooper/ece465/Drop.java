package edu.cooper.ece465;

import java.util.Queue;
import java.util.LinkedList;

public class Drop {
    // Message sent from producer
    // to consumer.
    private Queue<DropObj> messages;
    private int consumerCount = 0;
    private int producerCount = 0;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.

    public Drop(){
        this.messages = new LinkedList<DropObj>();
    }

    public synchronized void addCons(){
        this.consumerCount++;
        // notifyAll();
    }

    public synchronized void addProd(){
        this.producerCount++;
        // notifyAll();
    }

    public synchronized void rmCons(){
        this.consumerCount--;
        // notifyAll();
    }

    public synchronized void rmProd(){
        this.producerCount--;
        notifyAll();
    }

    public synchronized DropObj take() {
        // Wait until message is
        // available.
        while (messages.peek() == null && this.producerCount != 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Notify producer that
        // status has changed.
        notifyAll();

        if (this.producerCount == 0 && messages.size() == 0 ){
            return null;
        } else {
            return messages.remove();
        }
    }

    public synchronized void put(DropObj message) {
        // Wait until message has
        // been retrieved.
        while (messages.peek() != null) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Store message.
        this.messages.add(message);
        // Notify consumer that status
        // has changed.
        notifyAll();
    }
}
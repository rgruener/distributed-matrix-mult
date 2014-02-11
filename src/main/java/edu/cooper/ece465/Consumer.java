package edu.cooper.ece465;

import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;
    private int id;

    public Consumer(Drop drop, int id) {
        this.id = id;
        this.drop = drop;
        this.drop.addCons();
    }

    public double oneVal(Double[] row, Double[] col){
        double val = 0;
        for(int i = 0; i < row.length; i++){
            val +=row[i]*col[i];
        }
        return val;
    }

    public void run() {
        int numCon = 0;
        Random random = new Random();
        for (DropObj message = drop.take(); message != null; message = drop.take()) {
            double val = oneVal(message.row, message.col);
            try {
                Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {}
        }

        System.out.format("Consumer #%d consumed %d \n", this.id, numCon);

    }
}
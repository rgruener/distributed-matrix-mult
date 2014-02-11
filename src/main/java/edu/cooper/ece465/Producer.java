package edu.cooper.ece465;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;
    private int id;

    private static Double[][] A = {
            { 1.5, 2.5, 5.0},
            { 3.5, 14.7, 9.4},
            {19.4, 10.9, 5.0 }
    };
    private static Double[][] BTransposed = {
            { 1.5, 2.5, 5.0},
            { 3.5, 14.7, 9.4},
            {19.4, 10.9, 5.0 },
            { 3.5, 14.7, 9.4}
    }; // BTransposed transposed


    private static boolean [] thingsProduced = new boolean[A.length*BTransposed.length];

    public Producer(Drop drop, int id) {
        this.id = id;
        this.drop = drop;
        this.drop.addProd();
    }

    private synchronized boolean incrementThingToProduce(int row, int col){
        return thingsProduced[row*A.length + col] = true;
    }

    public void run() {
        int numProd = 0;
        Random random = new Random();

        for (int i=0; i<A.length; i++){
            for (int j=0; j< BTransposed.length; j++){
                if( !thingsProduced[i*A.length+j] ){
                    DropObj dropObj = new DropObj(A[i],BTransposed[j], i, j);
                    drop.put(dropObj);
                    incrementThingToProduce(i,j);
                }
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {}
            }
        }
        drop.rmProd();

        System.out.format("Producer #%d produced %d \n", this.id, numProd);
    }
}
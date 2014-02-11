package edu.cooper.ece465;

public class MyMatrix {

    Double[][] A = {
            { 1.5, 2.5, 5.0},
            { 3.5, 14.7, 9.4},
            {19.4, 10.9, 5.0 }
    };
    Double[][] BTransposed = {
            { 1.5, 2.5, 5.0},
            { 3.5, 14.7, 9.4},
            {19.4, 10.9, 5.0 },
            { 3.5, 14.7, 9.4}
    }; // BTransposed transposed





    public static Double[][] matMult(Double[][] A, Double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bColumns = B.length;
        int bRows = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match BTransposed:Columns " + bRows + ".");
        }

        Double[][] C = new Double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        return C;
    }

    public static void main(String[] args) {

        MyMatrix matrix = new MyMatrix();
        Double[][] result = matMult(matrix.A, matrix.BTransposed);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++)
                System.out.print(result[i][j] + "\t");
            System.out.println();
        }

        Drop drop = new Drop();

        for (int pNum = 1; pNum < 2 ; pNum++) {
            (new Thread(new Producer(drop, pNum))).start();
        }

        for (int cNum = 1; cNum < 5 ; cNum++) {
            (new Thread(new Consumer(drop, cNum))).start();
        }




    }

}










import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class SortingPerformance {

    public static void main(String[] args) throws IOException {
        int[] sizes = {50, 500, 1000, 5000, 10000};
        long seed = 68412;

        int numSwapsMerge = 0;
        int numIterationsMerge = 0;
        int numSwapsHeap = 0;
        int numIterationsHeap = 0;

        FileWriter csvWriter = new FileWriter("results.csv");
        csvWriter.append("Tamanho do Vetor,Algoritmo,Tempo de Execução (ms),Número de Trocas,Número de Iterações\n");

        for (int size : sizes) {
            int[][] arrays = generateRandomArrays(size, seed);

            for (int i = 0; i < 5; i++) {
                int[] arrCopy = Arrays.copyOf(arrays[i], arrays[i].length);

                // Merge Sort
                numSwapsMerge = 0;
                numIterationsMerge = 0;
                long startTimeMerge = System.nanoTime();
                SortingAlgorithms.merge(arrCopy, 0, arrCopy.length / 2, arrCopy.length - 1);
                long endTimeMerge = System.nanoTime();
                long durationMerge = endTimeMerge - startTimeMerge;

                csvWriter.append(size + ",Merge Sort," + (durationMerge / 1000000.0) + "," + SortingAlgorithms.numSwapsMerge + "," + SortingAlgorithms.numIterationsMerge + "\n");


                // Heap Sort
                numSwapsHeap = 0;
                numIterationsHeap = 0;
                arrCopy = Arrays.copyOf(arrays[i], arrays[i].length);
                long startTimeHeap = System.nanoTime();
                SortingAlgorithms.heapSort(arrCopy);
                long endTimeHeap = System.nanoTime();
                long durationHeap = endTimeHeap - startTimeHeap;

                csvWriter.append(size + ",Heap Sort," + (durationHeap / 1000000.0) + "," + SortingAlgorithms.numSwapsHeap + "," + SortingAlgorithms.numIterationsHeap + "\n");


                // Insertion Sort
                numSwapsMerge = 0; // Reutilizando para o Insertion Sort
                numIterationsMerge = 0;
                arrCopy = Arrays.copyOf(arrays[i], arrays[i].length);
                long startTimeInsertion = System.nanoTime();
                SortingAlgorithms.insertionSort(arrCopy);
                long endTimeInsertion = System.nanoTime();
                long durationInsertion = endTimeInsertion - startTimeInsertion;

                csvWriter.append(size + ",Insertion Sort," + (durationInsertion / 1000000.0) + "," + SortingAlgorithms.numSwapsMerge + "," + SortingAlgorithms.numIterationsMerge + "\n");

            }
        }

        csvWriter.flush();
        csvWriter.close();
    }

    public static int[][] generateRandomArrays(int size, long seed) {
        Random rand = new Random(seed);
        int[][] arrays = new int[5][size];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < size; j++) {
                arrays[i][j] = rand.nextInt(1000); // Gere números aleatórios entre 0 e 999
            }
        }

        return arrays;
    }
}

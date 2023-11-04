public class SortingAlgorithms {

    public static int numSwapsMerge = 0;
    public static int numIterationsMerge = 0;
    public static int numSwapsHeap = 0;
    public static int numIterationsHeap = 0;

    // Merge Sort
    public static void merge(int[] arr, int start, int mid, int end) {
        int p = start, q = mid + 1;
        int[] Arr = new int[end - start + 1];
        int k = 0;

        for (int i = start; i <= end; i++) {
            if (p > mid) {
                Arr[k++] = arr[q++];
            } else if (q > end) {
                Arr[k++] = arr[p++];
            } else if (arr[p] < arr[q]) {
                Arr[k++] = arr[p++];
            } else {
                Arr[k++] = arr[q++];
            }
        }

        for (int i = 0; i < k; i++) {
            arr[start++] = Arr[i];
        }

        numSwapsMerge += k - 1;
        numIterationsMerge++;
    }


    // Função para realizar o Heap Sort
    public static void heapSort(int[] arr) {
        int heapSize = arr.length;

        buildMaxHeap(arr);

        for (int i = heapSize - 1; i >= 1; i--) {
            swap(arr, 0, i);
            heapSize--;
            maxHeapify(arr, 0, heapSize);
        }
    }

    // Função para construir um Max-Heap
    private static void buildMaxHeap(int[] arr) {
        int heapSize = arr.length;
        for (int i = (heapSize / 2) - 1; i >= 0; i--) {
            maxHeapify(arr, i, heapSize);
        }
    }

    // Função para manter a propriedade de Max-Heap
    private static void maxHeapify(int[] arr, int i, int heapSize) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, heapSize);
        }

        numSwapsHeap++;
        numIterationsHeap++;
    }

    // Função para trocar elementos de posição no array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int temp = arr[i];
            int j = i;

            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j = j - 1;
            }

            arr[j] = temp;

            // Atualiza os contadores
            numSwapsMerge++;
            numIterationsMerge++;
        }
    }
}

import java.util.Arrays;

public class Main {
    public static void sort(int[] arr) {
        int n = arr.length;

        // Построить кучу (переставить массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Один за другим извлечь элемент из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Переместить текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // вызвать max heapify для уменьшенной кучи
            heapify(arr, i, 0);
        }
    }

    // Чтобы создать поддерево с корнем узла i, который является индексом в arr[]. n - размер кучи
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Инициализировать наибольший как корень
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // Если левый дочерний элемент больше, чем корень
        if (left < n && arr[left] > arr[largest])
            largest = left;

        //Если правый элемент больше, чем самый большой на данный момент
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // Если самый большой не корень
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Рекурсивно увеличить затронутое поддерево
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        Main.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
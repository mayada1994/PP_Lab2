public class CustomArray {
    public int[] array;
    public int currentSize;

    public void createArray(int size) {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i + 1;
        }
        currentSize = array.length;
    }

    public void printArray() {
        for (int j = 0; j < currentSize; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println("");
    }

    public int sumPair(int leftIndex, int rightIndex) {
        return array[leftIndex] + array[rightIndex];
    }

    public synchronized void addSum(int sum, int index) {
        array[index] = sum;
        notify();
    }

    public int sumArray() {
        int currentElement = this.currentSize - 1;
        while (currentSize > 1) {
            for (int i = 0; i < currentSize / 2; i++) {
                try {
                    ThreadSum thread = new ThreadSum(this, i, currentElement);
                    thread.start();
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentElement--;
            }

            currentSize = currentSize / 2 + currentSize % 2;
        }
        return array[0];
    }

}

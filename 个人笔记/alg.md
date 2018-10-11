# 手撕算法系列

## 查找

```java
public static int binarySearch(int target, int[] arr, int low, int high) {
    if (low > high)
        return -1;
    int middle;
    middle = (low + high) / 2;
    if (arr[middle] == target)
        return middle;
    else if (arr[middle] > target)
        return binarySearch(target, arr, low, middle - 1);
    else
        return binarySearch(target, arr, middle + 1, high);
}
```

## 排序

```java
public class Sort {
    public static int[] bubbleSort(int[] arr, int len) {
        for (int i = 0; i < len - 1; ++i)
            for (int j = 0; j < len - 1 - i; ++j)
                if (less(arr[j], arr[j + 1]))
                    swap(arr, j, j + 1);
        return arr;
    }

    public static int[] selectSort(int[] arr, int len) {
        for (int i = 0; i < len; ++i) {
            int min = i;
            for (int j = i; j < len; ++j)
                if (arr[j] > arr[min])
                    min = j;
            swap(arr, i, min);
        }
        return arr;
    }


    public static void insertSort(int[] arr, int len) {
        for (int i = 1; i < len; ++i) {
            int x = arr[i];
            int j = i - 1;
            while (j >= 0 && less(x, arr[j])) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = x;
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int low = left;
        int high = right;
        while (low < high) {
            while (arr[high] >= pivot && low < high)
                high--;
            arr[low] = arr[high];
            while (arr[low] <= pivot && low < high)
                low++;
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void mergeSort(int a[], int temp[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, temp, left, mid);
            mergeSort(a, temp, mid + 1, right);
            mergeArray(a, temp, left, mid, right);
        }
    }

    private static void mergeArray(int a[], int temp[], int left, int mid, int right) {
        System.arraycopy(a, left, temp, left, right - left + 1);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (temp[i] < temp[j]) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            a[k] = temp[i];
            i++;
            k++;
        }
        while (j <= right) {
            a[k] = temp[j];
            j++;
            k++;
        }
    }


    private static boolean less(int a, int b) {
        return a < b;
    }


    private static void swap(int[] arr, int i, int j) {
        int t;
        t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }
}

```

## 二叉树

```java
public class BinaryTree {
    private class TreeNode {
        private int index = 0;
        private String data;
        private Boolean isVisited = false;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode() {
        }

        public TreeNode(int index, String data) {
            this.index = index;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;

        }
    }

    private TreeNode root = null;

    public BinaryTree() {
        root = new TreeNode(1, "rootNode(A)");
    }

    public int getHeight() {
        return getHeight(root);
    }

    public int getHeight(TreeNode node) {
        if (node == null)
            return 0;
        int l = getHeight(node.leftChild);
        int r = getHeight(node.rightChild);
        return l > r ? (l + 1) : (r + 1);
    }

    public int getSize() {
        return getSize(root);
    }

    public int getSize(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftSize = getSize(node.leftChild);
        int rightSize = getSize(node.rightChild);
        return leftSize + rightSize + 1;
    }
}
```

## 生产者消费者

```java
/**
 * 生产者消费者的简单实现
 * 使用object的wait()和notify()
 *
 * @author Fururur
 * @create 2018-08-23-15:50
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        ProducerConsumer main = new ProducerConsumer();
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 5;
        new Thread(main.new Producer(buffer, maxSize), "Producer1").start();
        new Thread(main.new Consumer(buffer, maxSize), "Comsumer1").start();
        new Thread(main.new Consumer(buffer, maxSize), "Comsumer2").start();
    }

    class Producer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;

        Producer(Queue<Integer> queue, int maxSize) {
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full, " + Thread.currentThread().getName() + "is waiting for " + "consumer to take something from queue");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println(Thread.currentThread().getName() + " Producing value : " + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize) {
            super();
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty," + Thread.currentThread().getName() + " is waiting" + " for producer thread to put something in queue");
                            queue.wait();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " Consuming value : " + queue.remove());
                    queue.notifyAll();
                }
            }
        }
    }
}
```

## 单例模式

```java
public class Singleton {
    private static volatile Singleton instance = null;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
```


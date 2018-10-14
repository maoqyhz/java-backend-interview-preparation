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

## 判断链表带环

```java
/**
 * Function:是否是环链表，采用快慢指针，一个走的快些一个走的慢些 如果最终相遇了就说明是环
 * 就相当于在一个环形跑道里跑步，速度不一样的最终一定会相遇。
 *
 * @author crossoverJie
 *         Date: 04/01/2018 11:33
 * @since JDK 1.8
 */
public class LinkLoop {

    public static class Node{
        private Object data ;
        public Node next ;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Object data) {
            this.data = data ;
        }
    }

    /**
     * 判断链表是否有环
     * @param node
     * @return
     */
    public boolean isLoop(Node node){
        Node slow = node ;
        Node fast = node.next ;

        while (slow.next != null){
            Object dataSlow = slow.data;
            Object dataFast = fast.data;

            //说明有环
            if (dataFast == dataSlow){
                return true ;
            }

            //一共只有两个节点，但却不是环形链表的情况，判断NPE
            if (fast.next == null){
                return false ;
            }
            //slow走慢点  fast走快点
            slow = slow.next ;
            fast = fast.next.next ;

            //如果走的快的发现为空 说明不存在环
            if (fast == null){
                return false ;
            }
        }
        return false ;
    }
}
```

## 链表反转

```java
mport java.util.Stack;

/**
 * Function: 三种方式反向打印单向链表
 *
 * @author crossoverJie
 *         Date: 10/02/2018 16:14
 * @since JDK 1.8
 */
public class ReverseNode {


    /**
     * 利用栈的先进后出特性
     * @param node
     */
    public void reverseNode1(Node node){

        System.out.println("====翻转之前====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        System.out.println("====翻转之后====");
        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }

    }


    /**
     * 利用头插法插入链表
     * @param head
     */
    public  void reverseNode(Node head) {
        if (head == null) {
            return ;
        }

        //最终翻转之后的 Node
        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            //链表的头插法
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        //遍历新链表
        while (node != null){
            System.out.println(node.value);
            node = node.next ;
        }

    }


    /**
     * 递归
     * @param node
     */
    public void recNode(Node node){

        if (node == null){
            return ;
        }

        if (node.next != null){
            recNode(node.next) ;
        }
        System.out.print(node.value+"===>");
    }


    public static class Node<T>{
        public T value;
        public Node<T> next ;


        public Node(T value, Node<T> next ) {
            this.next = next;
            this.value = value;
        }
    }
}
```

## 合并两个有序链表

```java
/**
 * Function: 合并两个排好序的链表
 *
 * 每次比较两个链表的头结点，将较小结点放到新的链表，最后将新链表指向剩余的链表
 *
 * @author crossoverJie
 *         Date: 07/12/2017 13:58
 * @since JDK 1.8
 */
public class MergeTwoSortedLists {


    /**
     * 1. 声明一个头结点
     * 2. 将头结点的引用赋值给一个临时结点，也可以叫做下一结点。
     * 3. 进行循环比较，每次都将指向值较小的那个结点(较小值的引用赋值给 lastNode )。
     * 4. 再去掉较小值链表的头结点，指针后移。
     * 5. lastNode 指针也向后移，由于 lastNode 是 head 的引用，这样可以保证最终 head 的值是往后更新的。
     * 6. 当其中一个链表的指针移到最后时跳出循环。
     * 7. 由于这两个链表已经是排好序的，所以剩下的链表必定是最大的值，只需要将指针指向它即可。
     * 8. 由于 head 链表的第一个结点是初始化的0，所以只需要返回 0 的下一个结点即是合并了的链表。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0) ;
        ListNode lastNode = head ;

        while (l1 != null  && l2 != null){
            if (l1.currentVal < l2.currentVal){
                lastNode.next = l1 ;
                l1 = l1.next ;
            } else {
                lastNode.next = l2 ;
                l2 = l2.next ;
            }
            lastNode =lastNode.next ;
        }

        if (l1 == null){
            lastNode.next = l2 ;
        }
        if (l2 == null){
            lastNode.next = l1 ;
        }

        return head.next ;
    }


    public static class ListNode {
        /**
         * 当前值
         */
        int currentVal;

        /**
         * 下一个节点
         */
        ListNode next;

        ListNode(int val) {
            currentVal = val;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "currentVal=" + currentVal +
                    ", next=" + next +
                    '}';
        }
    }

}
```

## 两个栈实现队列

```java
import java.util.Stack;

/**
 * Function: 两个栈实现队列
 *
 * 利用两个栈来实现，第一个栈存放写队列的数据。
 * 第二个栈存放移除队列的数据，移除之前先判断第二个栈里是否有数据。
 * 如果没有就要将第一个栈里的数据依次弹出压入第二个栈，这样写入之后的顺序再弹出其实就是一个先进先出的结构了。
 *
 * 这样出队列只需要移除第二个栈的头元素即可。
 *
 * @author crossoverJie
 *         Date: 09/02/2018 23:51
 * @since JDK 1.8
 */
public class TwoStackQueue<T> {

    /**
     * 写入的栈
     */
    private Stack<T> input = new Stack() ;

    /**
     * 移除队列所出的栈
     */
    private Stack<T> out = new Stack() ;


    /**
     * 写入队列
     * @param t
     */
    public void appendTail(T t){
        input.push(t) ;
    }

    /**
     * 删除队列头结点 并返回删除数据
     * @return
     */
    public T deleteHead(){

        //是空的 需要将 input 出栈写入 out
        if (out.isEmpty()){
            while (!input.isEmpty()){
                out.push(input.pop()) ;
            }
        }

        //不为空时直接移除出栈就表示移除了头结点
        return out.pop() ;
    }


    public int getSize(){
        return input.size() + out.size() ;
    }

}
```



## 从一个数组中返回两个值相加等于目标值的下标

```java
import java.util.HashMap;
import java.util.Map;

/**
 * Function:{1,3,5,7} target=8 返回{2,3}
 *
 * @author crossoverJie
 *         Date: 04/01/2018 09:53
 * @since JDK 1.8
 */
public class TwoSum {

    /**
     * 时间复杂度为 O(N^2)
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwo1(int[] nums,int target){
        int[] result = new int[2] ;

        for (int i= 0 ;i<nums.length ;i++){
            int a = nums[i] ;
            for (int j = nums.length -1 ;j >=0 ;j--){
                int b = nums[j] ;

                if ((a+b) == target){
                    result = new int[]{i,j} ;
                }
            }
        }
        return result ;
    }


    /**
     * 时间复杂度 O(N)
     * 利用Map Key存放目标值和当前值的差值，value 就是当前的下标
     * 每次遍历是 查看当前遍历的值是否等于差值，如果是等于，说明两次相加就等于目标值。
     * 然后取出 map 中 value ，和本次遍历的下标，就是两个下标值相加等于目标值了。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] getTwo2(int[] nums,int target){
        int[] result = new int[2] ;
        Map<Integer,Integer> map = new HashMap<>(2) ;
        for (int i=0 ;i<nums.length;i++){

            if (map.containsKey(nums[i])){
                result = new int[]{map.get(nums[i]),i} ;
            }
            map.put(target - nums[i],i) ;
        }
        return result ;
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


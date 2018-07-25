package xyz.furur.prepare.algorithm;

/**
 * 查找算法
 *
 * @author Fururur
 * @create 2018-07-25-15:37
 */
public class Search {

    /**
     * 二分查找 - 非递归
     *
     * @param target
     * @param arr
     * @return
     */
    public static int binarySearch(int target, int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int middle;
        if (target > arr[high] || target < arr[low])
            return -1;
        while (low <= high) {
            middle = (low + high) / 2;
            if (arr[middle] == target)
                return middle;
            else if (arr[middle] < target)
                low = middle + 1;
            else
                high = middle - 1;
        }
        return -1;
    }

    /**
     * 二分查找 - 递归
     *
     * @param target
     * @param arr
     * @param low
     * @param high
     * @return
     */
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
}

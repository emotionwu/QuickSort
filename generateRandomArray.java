import java.util.Random;
//生成随机数组  长度为n的随机数组，每个数字的范围为[0,bound]
public class generateRandomArray {
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++)
            arr[i] = rnd.nextInt(bound);
        return arr;
    }

    public static Integer[] generateOrderdArray(int n)  //得到顺序数组
    {

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        return arr;
    }

    public static Integer[] generateSpecialArray(int n) {
        //开空间
        Integer[] arr = new Integer[n];
        //生成arr[0...n-1]的测试用例，其中最小值是0
        generateSpecialArray(arr, 0, arr.length - 1, 0);
        return arr;
    }

    //生成arr[l....r]的测试数据，其中最小值是V
    //如果每次都选择中间的元素做标定点，我们的快速排序将会退化。
    private static void generateSpecialArray(Integer[] arr, int l, int r, int value) {
        //把最小值放到最中间
        if (l > r) return;
        int mid = (l + r) / 2;
        arr[mid] = value;
        //模拟partition过程，把中间的元素和最左边的元素交换位置
        swap(arr, l, mid);
        //  处理除了最左边的元素之外，剩下的 n - 1 个元素；
        // 所以，处理的区间变成了 arr[l+1…r]，同时，最小值 + 1
        generateSpecialArray(arr, l + 1, r, value + 1);
        //  都处理好以后，还要把中间的元素和最左边的元素交换回来。
        swap(arr, l, mid);

    }

    //找到最小值索引后，交换两个值
    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private QuickSort(){}
    public static<E extends Comparable<E>>   void  sort(E[]arr){
           sort(arr,0,arr.length-1);
    }
    private static<E extends Comparable<E>> void sort(E[]arr,int l,int r){
        if(l>=r)  return;
       int p= partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }
    //利用插入排序法改善快速排序法，对底层的运用插入排序法
    public static<E extends Comparable<E>>   void  sort2(E[]arr) {
        Random rnd=new Random();
        sort2(arr, 0, arr.length - 1,rnd);
    }
        private static<E extends Comparable<E>> void sort2(E[]arr,int l,int r,Random rnd){
        if(r-l<=8)  {
            InsertionSort.sort4(arr,l,r);
            return;
        }
        int p= partition2(arr,l,r,rnd);
        sort2(arr,l,p-1,rnd);
        sort2(arr,p+1,r,rnd);
    }
    private static<E extends  Comparable<E>> int partition(E[]arr, int l,int r){
        //生成[l,r]之间的随机索引
            int p =l+ (new Random()).nextInt(r-l+1);
            swap(arr,l,p);
        //arr[l+1...j]<v; arr[j+1...i]>=v
        int j=l;
        for(int i=l+1;i<=r;i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
                swap(arr,l,j);
            return j;
    }
    //内存优化
    private static<E extends  Comparable<E>> int partition2(E[]arr, int l,int r,Random rnd){
        //生成[l,r]之间的随机索引
        int p =l+ rnd.nextInt(r-l+1);
        swap(arr,l,p);
        //arr[l+1...j]<v; arr[j+1...i]>=v
        int j=l;
        for(int i=l+1;i<=r;i++) {
            if (arr[i].compareTo(arr[l]) < 0) {
                j++;
                swap(arr, i, j);
            }
        }
        swap(arr,l,j);
        return j;
    }
    //双路快速排序法，解决数组中有很多元素相等的情况
    public static<E extends Comparable<E>>   void  sort2ways(E[]arr){
        sort2ways(arr,0,arr.length-1);
    }
    private static<E extends Comparable<E>> void sort2ways(E[]arr,int l,int r){
        if(l>=r)  return;
        int p= partition3(arr,l,r);
        sort2ways(arr,l,p-1);
        sort2ways(arr,p+1,r);
    }
    private static<E extends  Comparable<E>> int partition3(E[]arr, int l,int r){
        //生成[l,r]之间的随机索引
        int p =l+ (new Random()).nextInt(r-l+1);
        swap(arr,l,p);
       int i = l+1,j=r;
       while(true){
           while(i<=j && arr[i].compareTo(arr[l])<0)
               i++;
           while(j>=i && arr[j].compareTo(arr[l])>0)
               j--;
           if(i>=j) break;
           swap(arr,i,j);
           i++;
           j--;
       }
       swap(arr,l,j);
       return j;
    }
    //三路快速排序法
    public static<E extends Comparable<E>>   void  sort3ways(E[]arr) {
        Random rnd=new Random();
        sort3ways(arr, 0, arr.length - 1,rnd);
    }
    private static<E extends Comparable<E>> void sort3ways(E[]arr,int l,int r,Random rnd){
        if(l >= r)  return;
        //生成[l,r]之间的随即索引
        int p=l+rnd.nextInt(r-l+1);
        swap(arr,l,p);
        //arr[l+1,lt]<v, arr[lt+1,i-1]==v,arr[gt,r]>v
        int lt=l, i = l+1,gt=r+1;
        while(i<gt){
            if(arr[i].compareTo(arr[l])<0){
                lt++;
                swap(arr,i,lt);
                i++;
            }
            else if(arr[i].compareTo(arr[l])>0){
                gt --;
                swap(arr,i,gt);
            }
            else{
                //arr[i] ==arr[l]
                i++;
            }
        }
        swap(arr,l,lt);
        //arr[l,lt-1]<v, arr[lt,gt-1]==v,arr[gt,r]>v
        sort3ways(arr,l,lt-1,rnd);
        sort3ways(arr,gt,r,rnd);
    }

    //找到最小值索引后，交换两个值
    private static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    public static void main(String[]args){
        //对于完全有序的数组，n数量过大时，会出现栈溢出，调整运行中的虚拟参数为-Xss128m
        int n=100000;
        //无序
        Integer []arr= generateRandomArray.generateRandomArray(n,n);
        Integer[]arr1={4,3,5,6,1,2,8};
        Integer[]arr2= Arrays.copyOf(arr,arr.length);
        System.out.println("Random array:");
      //  SortingHelper.sortTest("MergeSort6",arr);
        SortingHelper.sortTest("QuickSort",arr);
        SortingHelper.sortTest("QuickSort2",arr2);
        SortingHelper.sortTest("QuickSort2ways",arr2);
        SortingHelper.sortTest("QuickSort3ways",arr2);
        System.out.println();
        //有序
        arr=generateRandomArray.generateOrderdArray(n);
        arr2= Arrays.copyOf(arr,arr.length);
        System.out.println("Ordered  array:");
      //  SortingHelper.sortTest("MergeSort6",arr);
        SortingHelper.sortTest("QuickSort",arr);
        SortingHelper.sortTest("QuickSort2",arr2);
        SortingHelper.sortTest("QuickSort2ways",arr2);
        SortingHelper.sortTest("QuickSort3ways",arr2);
        System.out.println();
        arr = generateRandomArray.generateSpecialArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        System.out.println("Special array:");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2", arr2);
        SortingHelper.sortTest("QuickSort2ways",arr2);
        SortingHelper.sortTest("QuickSort3ways",arr2);
        System.out.println();

    }
}

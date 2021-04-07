package SortingAlgorithm;
import java.util.*;
public class A_SortingAlgorithm小结 {
}

/**铁律：凡是input没有特殊性的data，想要全部sort，时间复杂度至少都是o(NlogN)*/

/*******************************************************************************************************************/
/**********************************************part 1***************************************************************/
/*******************************************************************************************************************/
/** O(N^2)级别SortingAlgorithm （都是comparison-based algorithm）
 * 1.Selection Sort
 * 2.Bubble Sort
 * 3.insertion Sort
 * */

/**
 * 1.Selection Sort
 * TC: O(N^2) -> O(n + n-1 + n-2 + …. + 4 +3 + 2 + 1)，等差数列的和 = (首数+尾数)*项数/2 = (n+1)*n/2 ~ n^2
 * sc: o(1)
 * */
/**e.g.each round remove the minimum element from the remainder of the list,
 *     and then insert it at the end of the values sorted so far.
 11 25 12 22 64   ->minimum: 12
 11 12 25 22 64   ->minimum: 22
 11 12 22 25 64
 * */
class A_SortingAlgorithm小结_SelectionSort {
    public int[] solve(int[] array) {
        // saity check
        if (array == null || array.length == 0) {
            return array;
        }
        int len = array.length;
        for (int i = 0; i <= len - 2; i++) {
            int minIdx = i;
            for (int j = i + 1; j <= len - 1; j++) {
                if (array[j] < array[minIdx]) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
        return array;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

/**2.Bubble Sort (不常考)
 * TC: O(N^2)
 * sc: o(1)
 * */
/**e.g.repeatedly swapping the adjacent elements if they are in wrong order.
 5 1 4 2 8 bubble down process(即each round把max swap到后面去)
 First Pass:
 ( 5 1 4 2 8 ) –> ( 1 5 4 2 8 ), Here, algorithm compares the first two elements, and swaps since 5 > 1.
 ( 1 5 4 2 8 ) –>  ( 1 4 5 2 8 ), Swap since 5 > 4
 ( 1 4 5 2 8 ) –>  ( 1 4 2 5 8 ), Swap since 5 > 2
 ( 1 4 2 5 8 ) –> ( 1 4 2 5 8 ), Now, since these elements are already in order (8 > 5), algorithm does not swap them.

 Second Pass:
 ( 1 4 2 5 8 ) –> ( 1 4 2 5 8 )
 ( 1 4 2 5 8 ) –> ( 1 2 4 5 8 ), Swap since 4 > 2
 ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 ( 1 2 4 5 8 ) –>  ( 1 2 4 5 8 )
 Now, the array is already sorted, but our algorithm does not know if it is completed.
 The algorithm needs one whole pass without any swap to know it is sorted.

 Third Pass:
 ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 ( 1 2 4 5 8 ) –> ( 1 2 4 5 8 )
 * */
class A_SortingAlgorithm小结_BubbleSort {
    public void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
}

/**
 * 3.insertion Sort
 * TC: O(N^2)
 * sc: o(1)
 * */
/**原理：
 * To sort an array of size n in ascending order:
 * 1: Iterate from arr[1] to arr[n] over the array.(用一个指针cur遍历)
 * 2: Compare the current element (key) to its predecessor.
 * 3: If the key element is smaller than its predecessor, compare it to the elements before. (将cur与他的predecessor从右往左依次比较，直到插入到cur所指元素应该在的位置)
 * Move the greater elements one position up to make space for the swapped element.
 *
 * e.g.
 * 12, 11, 13, 5, 6  ->i = 1.    Since 11 is smaller than 12, move 12 and insert 11 before 12
 * 11, 12, 13, 5, 6  ->i = 2.    13 will remain at its position as all elements in A[0..I-1] are smaller than 13
 * 11, 12, 13, 5, 6  ->i = 3.    5 will move to the beginning and all other elements from 11 to 13 will move one position ahead of their current position.
 * 5, 11, 12, 13, 6  ->i = 4.    6 will move to position after 5, and elements from 11 to 13 will move one position ahead of their current position.
 * 5, 6, 11, 12, 13
 * */
class A_SortingAlgorithm小结_InsertionSort {
    /*Function to sort array using insertion sort*/
    public void insertionSort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}

/*******************************************************************************************************************/
/**********************************************part 2***************************************************************/
/*******************************************************************************************************************/
/** O(NlogN)级别SortingAlgorithm （平均时间复杂度，有些最优最差TC有浮动）
 * 1.Merge Sort
 * 2.Quick Sort
 *      2.1 quick select (recursion + iterative)
 *          https://docs.google.com/document/d/1_2DnIjyu4N5pSol8PzDGMnwCVHHaG0JYwIDd1CGI9rU/edit
 *      2.2 Rainbow Sort / Counting Sort
 *          2.2.1 Rainbow Sort / Counting Sort iterative (用于要分的colors很少时)
 *          2.2.2 Rainbow Sort / Counting Sort recursion (用于要分的colors很多时)
 *
 * 3.Bucket sort
 * */

/**1.Merge Sort
 * TC：o(nlogn)
 *      slice部分：共log_2_n层,每一层slice的次数是 （等比数列求和！！！= a1(1-q^n)/(1-q)）
 *                O(1+2+4+8+......+n/2)         等比数列，这里共log_2_n项
 *                =1*（1 - 2 ^ log_2_n) / (1 - 2)
 *                =O(n-1)=O(n)           扔系数
 *      merge部分: 共log_2_n层，每层都遍历n个元素进行sorting，是：
 *                o(n*logn)
 *      所以最后是o(n + nlogn) = o(nlogn)
 * 空间：o(n)
 *      slice部分：recursion深度为log_2_n层，复杂度是call stack o(logn)
 *      merge部分：使用了长度为n的helper数组，将原输入数组抄写进去进行比较好，把sorting好的抄回原数组,空间是o(n）
 *      总的时间复杂度：o(n + logn) = o(n)
 * */
class A_SortingAlgorithm小结_MergeSort {
    public int[] mergeSort(int[] array) {
        // sanity check
        if(array == null || array.length == 0) {
            return new int[0];
        }
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    //recursion
    private void mergeSort(int[] array, int left, int right) {
        //base case
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, 0, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }

    private void merge(int[] array, int left, int mid, int right) {
        int[] copy = new int[array.length];
        for (int i = left; i <= right; i++) {
            copy[i] = array[i];
        }

        int i = left;
        int j = mid + 1;
        while (i <= mid && j <= right) {
            if (copy[i] < copy[j]) {
                array[left++] = copy[i++];
            } else {
                array[left++] = copy[j++];
            }
        }
        //post
        while (i <= mid) {
            array[left++] = copy[i++];
        }
    }

    public static void main(String[] args) {
        SortingAlgorithm.MergeSort.mergeSort ob = new SortingAlgorithm.MergeSort.mergeSort();
        int[] arr = new int[]{3, 1, 2};
        ob.mergeSort(arr);
        for (int i :arr) {
            System.out.println(i);
        }
    }
}

/**2.Quick Sort
 * TC:
 * 最优 O(nlogn) ->pivot恰好选择了一个中间值,recursion共log_2_n层，每层使用O(n)进行partition
 * 最差 O(n^2)   ->pivot恰好选择了一个极值,recursion共n层，每层使用O(n)进行partition
 * SC:
 * ave: o(logn)      //recursion，使用空间每次减半
 * worst:o(n)        //recursion,有call stack
 * */
/**原理解释：(quickSort的精彩在于理解partition()
 *           其实就是recursion每层找pivot，再每层花费O（n）进行partition,partition以后，比pivot小的都在pivot左边(unsort)，比pivot大的都在pivot右边(unsort)
 *           一直recursion结束就全部sorted了)
 * Step 1 − Make any element as pivot
 * Step 2 − Partition the array on the basis of pivot
 *          Partirion目的：Partition the array on the basis of pivot，to rearranges the array in such a way that pivot comes to its actual position (of the sorted array).
 *                        To left of the pivot, the array has all the elements less than it, and to the right greater than it.
 *          Partirion做法：move pivot to rightMost position, then we use two pointers i, j start from 0 & len - 1 respectively.
 *                        if(num[i]<pivot), update i
 *                        if (num[j]>pivot), update j
 *                        else swap(nums[i], num[j])
 *                        when i, j meet together, swap back pivot with current i idx
 * Step 3 − Apply quick sort on left partition recursively
 * Step 4 − Apply quick sort on right partition recursively
 * */
/**e.g.
 * https://docs.google.com/document/d/1rZ7URu1tbU37hDYyLS_AyRJepy8TWNWxobH_6loWhLc/edit
 * */
class A_SortingAlgorithm小结_QuickSort {
    Random random = new Random();
    public int[] quickSort(int[] array) {
        // sanity check
        if (array == null || array.length == 0) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    //recursion
    private void quickSort(int[] array, int left, int right) {
        //base case
        if (left >= right) {
            return;
        }
        int pivotIdx = partition(array, left, right);
        quickSort(array, left, pivotIdx - 1);
        quickSort(array, pivotIdx + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        int pivotVal = array[pivotIdx];
        swap(array, pivotIdx, right);

        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] < pivotVal) {
                i++;
            } else if (array[j] > pivotVal) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

/**2.1 quick select
 * https://docs.google.com/document/d/1_2DnIjyu4N5pSol8PzDGMnwCVHHaG0JYwIDd1CGI9rU/edit
 * quickSelect实质：就是用了quicjSort前面一部分，pivot的方法，去求target在哪里的问题
 * TC:      average: o(n)
 *          worst case: o(n ^ 2)
 * SC:      o(1)   //iterative
 *          ave: o(logn)      //recursion，使用空间每次减半
 *          worst:o(n)        //recursion,有call stack，每次减1
 * */
/***
 *与binary search的不同：
 * 唯一不同的步骤就是进行divide的方法（quickSelect是通过pivot，而binary search是通过与array[mid]相关的方程）
 * 所以可以看到，当quickSelect改写成iterative的方法时候，pivot和binarysearch中的mid类似，只是这里的pivot要用一个单独的partition helper函数产生以下，其余部分和binary search几乎一致
 *
 * 与binary search的相似：
 * we can guarantee the answer is in one of the partition
 */

class A_SortingAlgorithm小结_QuickSelect {
    /**recursion框架 (以find kth smallest ele in arr为例)*/
    public int kthRecursion(int[] array, int k, int left, int right) {
        if (left > right) {
            return 0;     //not necessary if k is valid
        }
        int pivot = partition(array, left, right);   //相当于取mid，只是不知道mid在哪里
            //pivot is the index in the original array.
            //[left……..pivot……..right]

            //判断pivot与k的关系
        if (k - 1 == pivot) {
            return pivot;
        } else if (k - 1 > pivot) {
            return kthRecursion(array, k, pivot + 1, right);
        }
        return kthRecursion(array, k, left, pivot - 1);
    }

    /**iterative框架 (以find kth smallest ele in arr为例)*/
    public int kthIterative(int[] array, int k, int left, int right) {
        while( left <= right) {
            int pivot = partition(array, left, right);
            //pivot is the index in the original array.
            //[left……..pivot……..right]

            //判断pivot与k的关系
            if (k-1 == pivot) {
                return pivot;
            } else if (k-1 > pivot) {
                return left = pivot + 1;
            }
            return right = pivot - 1;
        }
        return -1;
    }

    Random random = new Random();
    private int partition(int[] array, int left, int right) {
        int pivotIdx = left + random.nextInt(right - left + 1);
        int pivotVal = array[pivotIdx];
        swap(array, pivotIdx, right);

        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] < pivotVal) {
                i++;
            } else if (array[j] > pivotVal) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        swap(array, i, right);
        return i;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

/**2.2.1 Rainbow Sort / Counting Sort iterative (用于要分的colors很少时)
 * TC: O(N)
 * SC:O(1)
 * */
class A_SortingAlgorithm小结_RainbowSortIterative {
    public void sortColors(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
                swap(nums, j, k);
                k--;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

/**2.2.2 Rainbow Sort / Counting Sort recursion (用于要分的colors很多时)*/
/**
 * 原理：基于quick sort的partition
 * 假设有待sort数据区间[0, colors.length - 1], 要分成的color区间[1,2, 3...... k]
 * 使用recursion思想，each round找到color区间的mid point（midColor） ,待sort数据区间中<= color 的去左边，>color 的去右边
 *
 * n --> 待sort数据区间元素个数
 * k --> color区间颜色数目
 * TC: O(NlogK)    每次midColor左右两边分到的数据完美均衡，则recursion有logK层，每层最多需要O(N)进行sort
 * SC: O(logK）
 * */
class A_SortingAlgorithm小结_RainbowSortRecursion {
    public void sortColors2(int[] colors, int k) {
        // sanity check
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    /**
     left, right: cur round unsorted data range left & right bound
     leftColor, rightColor: cur round color range left & right bound
     */
    private void rainbowSort(int[] colors, int left, int right, int leftColor, int rightColor) {
        //base case
        if (leftColor == rightColor) {
            return;
        }
        if (left >= right) {
            return;
        }

        int midColor = leftColor + (rightColor - leftColor) / 2;
        int l = left;
        int r = right;
        while (l <= r) {
            //update pointer where do not need swap
            while (l <= r && colors[l] <= midColor) {
                l++;
            }
            while (l <= r && colors[r] > midColor) {
                r--;
            }
            //else : swap
            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
            }
        }
        //recursion
        rainbowSort(colors, left, r, leftColor, midColor);
        rainbowSort(colors, l, right, midColor + 1, rightColor);
    }
}

/**Bucket Sort*/
/**应用场景：input is uniformly distributed over a range 输入在某固定范围内均匀分布
 * 原理：
 * bucketSort(arr[], n)
 * 1) Create n empty buckets (Or lists).   ->O(n)
 * 2) Do following for every array element arr[i].  ->O(n)
 *           Insert arr[i] into bucket[n*array[i]]  (每个bucket所装的data range是根据input来自定义的)
 * 3) Sort individual buckets using insertion sort. ->O(1)到O（n^2)不等，根据input和所使用的的sort algorithm决定
 * 4) Concatenate all sorted buckets. ->O(n)
 *
 * TC:
 * 最优：o(n)
 * 最差：o(n^2)
 * 由上面可看出，TC主要由step 3来决定。能碰到一些题目最优解法使用bucket sort，tc能达到O(N)，这都是由于input或者output有特殊性才能达到的
 * case 1：arr[i]分配进入bucket以后，每个bucket只有一个元素
 * case 2：arr[i]分配进入bucket的顺序，直接得到需要的"sort"顺序，不需要额外sort每个bucket
 * 此时bucket sort TC = O(N)
 *
 * SC:
 *
 * */
class A_SortingAlgorithm小结_BucketSort {
    public void bucketSort(float arr[], int n) {
        if (n <= 0) {
            return;
        }
        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new Vector<Float>();
        }
        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }
        // 3) Sort individual buckets
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }
    }
}


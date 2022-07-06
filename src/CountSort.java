import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2022-2022/6/24
 * 计数排序测试
 */

public class CountSort {
    public static int[] sort(int[] array) {
        int len = array.length;
        if (len <= 1)
            return array;
        int[] countArray = new int[15464691];
        ArrayList<int[]> list = new ArrayList<>();
        int[] sortedArray = new int[len];
        for (int i = 0; i < len; i++) {
            countArray[array[i]]++;
        }
        int idx = 0;
        for (int i = 0; i < 15464691; i++) {
            for (int j = 0; j < countArray[i]; j++, idx++) {
                sortedArray[idx] = i;
            }
        }
        return sortedArray;
    }

    @Test
    //Arrays.sort()测试
    public void test2(){
        int[] array = ThreadedBitonicTest.getArray(10);
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    //计数排序测试
    public void test1(){
        int[] array = ThreadedBitonicTest.getArray(10);
        System.out.println(Arrays.toString(array));

        long start = System.currentTimeMillis();
        int[] sortedArray = CountSort.sort(array);
        System.out.println(System.currentTimeMillis() - start);

        System.out.println(Arrays.toString(sortedArray));
    }

}

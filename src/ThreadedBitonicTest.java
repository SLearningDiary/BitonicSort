import java.util.Arrays;

//多线程双调排序测试类
public class ThreadedBitonicTest {

    //    获得指定长度（size），取值范围（0~size）的随机数组
    public static int[] getArray(int size) {
        int[] array = new int[size];
        for (int index = 0; index < size; index++) {
            array[index] = (int) (Math.random() * size + 1);
        }
        return array;
    }

    public static void main(String[] args) {
//        1、指定数组
//        int[] array = {5, 2, 1, 7, 3, 8, 6, 4};

//        2、指定长度，随机数组
//        int size = (int) Math.pow(2, 20);
        int size = 9999999;
        int[] array = getArray(size);

        Bitonic b = new Bitonic();
        long start = System.currentTimeMillis();// 计时
        b.sort(array, Bitonic.ASCENDING);   //开始排序
        System.out.println("\n排序用时" + (System.currentTimeMillis() - start) + " ms\n");

        b.getInfo();
//        System.out.println(Arrays.toString(array));
    }
}

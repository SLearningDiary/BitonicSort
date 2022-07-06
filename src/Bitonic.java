//双调排序主类
public class Bitonic {
    // 数据排序方式
    public final static boolean
            ASCENDING = true, DESCENDING = !ASCENDING;//默认为升序

    public static int minimumLength;// 新建线程处理的最小数据长度
    public static int[] numbers;// 待排序的序列，为各线程的共享数据

    // 用以衡量算法效率
    private final int numberOfProcessors = 8; //默认为8
    public static int threadUsed ;

    public void sort(int[] numberSet, boolean direction) {
        // 双调排序前提是数组长度为 2的整数次幂,如果不是需要padding填充
        int[] initArray = padding(numberSet);

        numbers = initArray;
        minimumLength = numbers.length / numberOfProcessors;

        // 开始排序
        sort(numbers.length, direction);

        System.arraycopy(initArray, 0, numberSet, 0, numberSet.length);
    }

    private void sort(int size, boolean direction) {
        BitonicSortRunnable btr = new BitonicSortRunnable();
        Thread bt = new Thread(btr);
        bt.start();
        btr.sort(0, size, direction);
    }

//    按照需要的顺序调整两数字的位置
    public static void compare(int x, int y, boolean direction) {
        if (direction == (numbers[x] > numbers[y])) {
            swap(x, y);
        }
    }

//    交换数组中指定位置的元素
    public static void swap(int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

//    补全数组长度为2的整数次幂
    private static int[] padding(int[] array) {
        int length = array.length;

        //如果array长度为2的幂（在二进制只有一个1，与减一按位与为0），则不需要补丁操作
        if ((length & (length - 1)) == 0)
            return array;

        int adjustSize = 1 << 1;
        int paddingLength;
        while (adjustSize < length) {
            adjustSize <<= 1;
        }
        paddingLength = adjustSize - length;
        int[] paddingArray = new int[paddingLength];
        for (int i = 0; i < paddingLength; i++) {
            paddingArray[i] = Integer.MAX_VALUE;
        }
        int[] mergeArray = new int[adjustSize];
        System.arraycopy(array, 0, mergeArray, 0, length);
        System.arraycopy(paddingArray, 0, mergeArray, length, paddingLength);
        return mergeArray;
    }

//    获得排序相关信息
    public void getInfo() {
        if (numbers == null)
            return;
        System.out.println("Processor count: " + numberOfProcessors);
        System.out.println("Total threads created: " + threadUsed);
        System.out.println("Finished!\n");
    }
}

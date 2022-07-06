public class BitonicSortRunnable implements Runnable {
    private int _index; // 待处理数据在原数组中的开始下标
    private int _size;  // 数据长度
    private boolean _direction; //排序方式

    public BitonicSortRunnable() {
    }

    public BitonicSortRunnable(int index, int size, boolean direction) {
        _index = index;
        _size = size;
        _direction = direction;
    }

    @Override
    public void run() {
        Bitonic.threadUsed++;
        sort(_index, _size, _direction);
    }

    public synchronized void sort(int index, int size, boolean direction) {
        if (size <= 1)
            return;
        int median = size / 2;
        if (size > Bitonic.minimumLength) {
            Thread btLeft = new Thread(new BitonicSortRunnable(index, median, Bitonic.ASCENDING));
            Thread btRight = new Thread(new BitonicSortRunnable(index + median, median, Bitonic.DESCENDING));

//            btLeft.setName("Thread btLeft");// 仅用于标记多线程调试使用
//            btRight.setName("Thread btRight");

            btLeft.start();
            btRight.start();
            try {
                btLeft.join(); //等待该线程执行完成
                btRight.join();
            } catch (InterruptedException e) {
                System.out.println("Execution failed.");
            }
        } else {
            if (median > 1) {
                sort(index, median, Bitonic.ASCENDING);
                sort(index + median, median, Bitonic.DESCENDING);
            }
        }

        BitonicMergeRunnable bmt = new BitonicMergeRunnable();
        bmt.merge(index, size, direction);
    }
}

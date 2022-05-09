package com.sys.test.websocket.util.test;

/**
 * 快速排序
 */
public class QuickSortPrac {
    public static void main(String[] args) {
        testQuickSort();
    }

    /**
     * 快速排序
     */
    private static void testQuickSort() {
        int[] array = {5, 9, 0, 6, 1, 9, 5, 3, 3, 3, 7, 6, 1, 3, 3, -2, -5, 99, -99, 111};
        QuickSort quickSort = new QuickSort(array);
        quickSort.sort();
        quickSort.print();
    }

    static public class QuickSort {
        private int[] array;

        public QuickSort(int[] array) {
            this.array = array;
        }

        public void sort() {
            quickSort(array, 0, array.length - 1);
        }

        public void print() {
            for (int i = 0; i < array.length; i++) {
                System.out.println(array[i]);
            }
        }

        /**
         * 递归排序
         *
         * @param src
         * @param begin
         * @param end
         */
        private void quickSort(int[] src, int begin, int end) {
            int p = begin;
            if (begin < end) {
                int i = begin;
                int j = end;
                while (i < j) {//基准值是src[i]
                    while (i < j) {
                        //先从右边开始右边的值比左边的小进行交换操作，将此值交换到基准值左边
                        if (src[i] > src[j]) {
                            int tmp = src[i];
                            src[i] = src[j];
                            src[j] = tmp;
                        }
                        j--;
                    }
                    while (i < j) {//基准值是src[j]
                        //再从左边开始，找左边比基准大的
                        if (src[i] > src[j]) {
                            int tmp = src[j];
                            src[j] = src[i];
                            src[i] = tmp;
                        }
                        i++;
                    }
                }
                p = i;
                quickSort(src, begin, p - 1);
                quickSort(src, p + 1, end);
            }
        }
    }
}

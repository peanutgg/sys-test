package test.Thread;

import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) throws Exception {

        TestThread_Callable task1 = new TestThread_Callable();
        FutureTask<Integer> result = new FutureTask<Integer>(task1);
        FutureTask<Integer> result2 = new FutureTask<Integer>(task1);
        FutureTask<Integer> result3 = new FutureTask<Integer>(task1);
        FutureTask<Integer> result4 = new FutureTask<Integer>(task1);
        System.out.println("�߳�����main---" + Thread.currentThread().getName());
        Thread t1 = new Thread(result);
        t1.start();
        new Thread(result2).start();
        new Thread(result3).start();
        new Thread(result4).start();
        System.out.println(result.get());
        System.out.println(result2.get());
    }

    /**
     * ����һ���ַ����е�һ�����ظ����ַ�
     *
     * @param str
     * @return
     */
    static char getC(String str) {

        boolean flag = false;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                if (j == i) {
                    continue;
                }
                if (chars[i] == chars[j]) {
                    flag = true;
                }
                if (j == chars.length - 1 && !flag) {
                    return chars[i];
                }
            }
            flag = false;
        }
        return '1';
    }
}

package test.Thread;

import java.util.Scanner;

public class Test5 {
    static int[] arr = new int[10000];
    static Scanner input = new Scanner(System.in);
    static int num;
    static int num2 = 1000;
    public static void main(String[] args) {

//        System.out.println("������һ��Ҫ�ֽ����:");
//        num = input.nextInt();
//        search(num, 1, 0);
        fbnq(1, 1000);
    }

    private static void search(int n, int s, int x) {

        if (n > 0) {
            for (int i = s; i <= n; i++) {
                arr[x] = i;
                search(n - i, i, x + 1);
            }
            return;
        }
        System.out.print(num + "=" + arr[0]);
        for (int i = 1; i < x; i++) {
            System.out.print("+" + arr[i]);
        }
        System.out.println();

    }


    public static void fbnq(int pre,int nums) {

        if (nums != 0) {
            System.out.println(pre + pre + 1);
            fbnq(pre + 1,--nums);
        }
        return;

    }

}

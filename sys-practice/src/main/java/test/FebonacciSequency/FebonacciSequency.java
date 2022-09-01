package test.FebonacciSequency;

public class FebonacciSequency {
    public static void main(String[] args) {

        int tier = 0;
        for (; tier < 10; tier++) {
            System.out.println(febonacci(tier));
        }
    }

    static int febonacci(int n) {

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return febonacci(n - 1) + febonacci(n - 2);
    }
    //f 3
    //f 2 + f 1
    //f 1 + f 0 + 1

}

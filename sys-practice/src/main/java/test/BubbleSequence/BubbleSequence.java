package test.BubbleSequence;

public class BubbleSequence {
    public static void main(String[] args) {
        int [] arr = new int[]{10,1,35,61,89,36,55};
        seq(arr);

    }

    public static void seq(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}

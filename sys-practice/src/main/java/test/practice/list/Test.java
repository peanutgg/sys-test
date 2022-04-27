package test.practice.list;

public class Test {
    public static void main(String[] args) {
        MyList<Integer> arr = new MyList<>();


        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);

        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }

        arr.remove(0);
        for (int i = 0; i < arr.getSize(); i++) {
            System.out.println(arr.get(i));
        }
    }
}

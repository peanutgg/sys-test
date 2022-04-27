package test.practice.stack;

public class Test {
    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<>();

        s.push(0);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        System.out.println(s.index.get() + "---------");
        while (s.index.get() >= 0) {
            System.out.println(s.pop());
        }
    }
}

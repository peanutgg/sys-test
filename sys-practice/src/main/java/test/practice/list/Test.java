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

        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> m2 = new HashMap<>();
        m1.put("k1", 1);
        m1.put("k2", 2);
        m1.put("k3", 3);
        m1.put("k5", 5);
        m2.put("k1", 1);
        m2.put("k2", 2);
        m2.put("k3", 3);
        m2.put("k4", 4);
        m1.forEach((key, value) -> m2.merge(key, value, (v1, v2) -> m1.get(key) + m2.get(key)));
        m2.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}

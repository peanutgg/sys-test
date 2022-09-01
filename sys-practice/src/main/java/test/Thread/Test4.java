package test.Thread;

import java.util.LinkedList;

public class Test4 {
    public static void main(String[] args) {
        String []  arr = new String[]{"1","2","3","4","5","4","6","7","1"};
        LinkedList list = new LinkedList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (list.contains(arr[i])) {
                sb.append("arr��" + arr[i] + "�ظ�");
            }else{
                list.add(arr[i]);
            }
        }
        System.out.println(sb.toString());
    }
}

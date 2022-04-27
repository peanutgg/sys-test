package test.TwentyTwentyOne;

import java.util.Stack;

public class StackLIFO {


    public static void main(String[] args) {


        Stack<Character> t = new Stack<>();
        String s = "(){}[]";
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);

            if(c=='('){
                t.push(c);
            }
            




        }
    }
}

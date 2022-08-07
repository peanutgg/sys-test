import inf.Say;

import java.util.ServiceLoader;

/**
 * @author: 橘子
 * @date: 2022/6/10 15:59
 */
public class Main {
    public static void main(String[] args) {
        //获取所有的provider
//        ServiceLoader<Say> says = ServiceLoader.load(Say.class);
//        says.forEach(s->{
//            s.say();});
        String str = "sdfsdfsad\r" +
                "sadfsdaf\r" +
                "asdfsdfasdf\r";
        StringBuffer sb = new StringBuffer();
        sb.append('c');
        sb.append('a');
        sb.append('c');
        System.out.println(sb.toString());

        String[] split = str.split("\r");
        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }
}

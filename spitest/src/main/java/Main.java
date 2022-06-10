import inf.Say;

import java.util.ServiceLoader;

/**
 * @author: 橘子
 * @date: 2022/6/10 15:59
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Say> says = ServiceLoader.load(Say.class);
        says.forEach(s->{
            s.say();});
    }
}

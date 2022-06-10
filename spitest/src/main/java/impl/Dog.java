package impl;

import inf.Say;

/**
 * @author: 橘子
 * @date: 2022/6/10 15:57
 */
public class Dog implements Say {
    @Override
    public void say() {
        System.out.println("dog");
    }
}

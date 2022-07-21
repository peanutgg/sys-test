package com.sys.juc.test.future;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: 橘子
 * @date: 2022/7/13 16:15
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<List<String>> total = CompletableFuture.supplyAsync(() -> {
            // 第一个任务获取美术课需要带的东西，返回一个list
            List<String> stuff = new ArrayList<>();
            stuff.add("画笔");
            stuff.add("颜料");
            return stuff;
        }).thenCompose(list -> {
            // 向第二个任务传递参数list(上一个任务美术课所需的东西list)
            System.out.println("-------------------" + list.size());
            CompletableFuture<List<String>> insideFuture = CompletableFuture.supplyAsync(() -> {
                List<String> stuff = new ArrayList<>();
                // 第二个任务获取劳技课所需的工具
                stuff.add("剪刀");
                stuff.add("折纸");
                // 合并两个list，获取课程所需所有工具
                List<String> allStuff = Stream.of(list, stuff).flatMap(Collection::stream).collect(Collectors.toList());
                return allStuff;
            });
            return insideFuture;
        });
        System.out.println(total.join().size());
    }
}

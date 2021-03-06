package com.sys.test.service.a.hystrix.request.merge;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.sys.test.service.a.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<UserInfo>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBatchCommand.class);


    private List<Integer> ids;

    public UserBatchCommand(List<Integer> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup")).
                andCommandKey(HystrixCommandKey.Factory.asKey("GetValueForKey")));
        this.ids = ids;
    }

    /**
     * 调用批量处理的方法
     *
     * @return
     */
    @Override
    protected List<UserInfo> run() {
        List<UserInfo> users = Arrays.asList();
        return users;
    }

    /**
     * Fallback回调方法，如果没有会报错
     */
    @Override
    protected List<UserInfo> getFallback() {
        System.out.println("UserBatchCommand的run方法，调用失败");
        return null;
    }
}


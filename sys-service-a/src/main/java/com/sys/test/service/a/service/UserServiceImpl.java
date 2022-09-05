package com.sys.test.service.a.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sys.test.service.a.entity.UserInfo;
import com.sys.test.service.a.feignclient.MyBServiceApi;
import com.sys.test.service.a.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    MyBServiceApi bServiceApi;

    public int addUser(String name) {
        return userInfoMapper.insert(new UserInfo(name));
    }

    public List<UserInfo> getUser(Integer id) {
        return userInfoMapper.selectUsers(id);
    }

    //    //服务降级
//    @HystrixCommand(fallbackMethod = "test_timeOutHandler",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
//            })
    @Override
    public String test_timeOut(Integer id) {
        return bServiceApi.test_timeOut(id);
    }

//    public String test_timeOutHandler(Integer id) {
//        return "b-service time out handler.." + id;
//    }

    @Override
    public String test_timeOut2(Integer id) {
        return bServiceApi.test_timeOut2(id);
    }

    /**
     * 以service实现类ProductServiceImpl中的selectById方法为例子，来配置熔断信息。其中fallbackMethod配置的降级函数，
     * "circuitBreaker.enabled" 配置是否开启服务熔断
     * "circuitBreaker.requestVolumeThreshold"为时间窗口内的请求阈值，只有达到这个阈值，才会判断是否打开断路器。比如配置为10次，那么在时间窗口内请求9次，9次都失败了也不会打开断路器
     * "circuitBreaker.sleepWindowInMilliseconds"为时间窗口，当断路器打开后，会根据这个时间继续尝试接受请求，如果请求成功则关闭断路器。
     * "circuitBreaker.errorThresholdPercentage"为配置的失败比率，在时间窗口内请求次数达到请求阈值，并且失败比率达到配置的50%，才会打开断路器。
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
    })
    public String test_timeOut3(Integer id) {
        return bServiceApi.test_timeOut3(id);
    }

}

package com.sys.test.service.a.fallback;

import com.sys.test.systestservice.b.api.BServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * b-service 降级
 * 降级是指，当请求超时、资源不足等情况发生时进行服务降级处理，
 * 不调用真实服务逻辑，而是使用快速失败（fallback）方式直接返回一个托底数据，
 * 保证服务链条的完整，避免服务雪崩。
 */
@Component
public class BServiceApiFallbackFactory implements FallbackFactory<BServiceApi> {

    @Override
    public BServiceApi create(Throwable cause) {
        return new BServiceApi() {
            @Override
            public String test_timeOut(Integer id) {
                return "b-service test_timeOut fallback";
            }

            @Override
            public String test_timeOut2(Integer id) {
                return "b-service test_timeOut2 fallback";
            }

            @Override
            public String test_timeOut3(Integer id) {
                return "b-service test_timeOut3 fallback";
            }
        };
    }
}

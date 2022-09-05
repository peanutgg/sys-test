package com.sys.test.service.a.feignclient;

import com.sys.test.service.a.fallback.BServiceApiFallbackFactory;
import com.sys.test.systestservice.b.api.BServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "service-b", fallbackFactory = BServiceApiFallbackFactory.class)
public interface MyBServiceApi extends BServiceApi {
}

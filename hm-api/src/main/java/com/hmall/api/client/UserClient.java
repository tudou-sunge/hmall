package com.hmall.api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author sunshuxian
 * @date 2023-11-21 18:16
 */
@FeignClient("user-service")
public interface UserClient {

    @PutMapping("/users/money/deduct")
    public void deductMoney(@RequestParam("pw") String pw, @RequestParam("amount") Integer amount);
}

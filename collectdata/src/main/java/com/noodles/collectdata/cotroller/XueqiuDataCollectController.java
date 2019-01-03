package com.noodles.collectdata.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("test")
public class XueqiuDataCollectController {

    /**
    * @Description:
    * @Param:
    * @return:
    * @Author: Eric
    * @Date: 2018/12/25
    */
    @GetMapping("{msg}")
    public Mono<String> sayHelloWorld(@PathVariable("msg") String msg) {
        System.out.println("say: " + msg);
        return Mono.just("springcloud-provider receive : " +msg);
    }
}

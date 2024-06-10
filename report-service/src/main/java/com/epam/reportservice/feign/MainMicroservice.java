//package com.epam.reportservice.feign;
//
//import com.epam.reportservice.config.MainMicroserviceConfig;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.HttpHeaders;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestHeader;
//
//@FeignClient(value = "main-microservice", path = "/validate", configuration = MainMicroserviceConfig.class)
//public interface MainMicroservice {
//    @GetMapping
//    boolean validate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
//
//}

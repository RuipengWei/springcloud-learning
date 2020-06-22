package com.ruipeng;

import brave.sampler.Sampler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@Slf4j
public class ServiceMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMiyaApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hi")
    public String home() {
        log.info("hi is being called");
        return "hi i am miya";
    }

    @GetMapping("/miya")
    public String info() {
        log.info("info is being called");
        return restTemplate.getForObject("http://localhost:8988/info", String.class);
    }
}

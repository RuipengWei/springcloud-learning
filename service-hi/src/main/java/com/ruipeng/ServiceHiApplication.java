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
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
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
    public String callHome() {
        log.info("calling trace service-hi");
        return restTemplate.getForObject("http://localhost:8989/miya", String.class);
    }

    @GetMapping("/info")
    public String info() {
        log.info("calling trace service-hi");
        return "I'm service-hi";
    }

}

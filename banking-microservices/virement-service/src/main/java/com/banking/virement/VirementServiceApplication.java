package com.banking.virement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VirementServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VirementServiceApplication.class, args);
    }
}

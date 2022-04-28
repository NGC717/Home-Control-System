package com.lq.housesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HouseSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseSystemApplication.class, args);
    }

}

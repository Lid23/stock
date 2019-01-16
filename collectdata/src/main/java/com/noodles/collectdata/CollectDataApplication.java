package com.noodles.collectdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = { "com.noodles.collectdata.dao" })
public class CollectDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollectDataApplication.class, args);
	}

}


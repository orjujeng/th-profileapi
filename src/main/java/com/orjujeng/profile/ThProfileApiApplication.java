package com.orjujeng.profile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.orjujeng.profile.mapper")
public class ThProfileApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThProfileApiApplication.class, args);
	}

}

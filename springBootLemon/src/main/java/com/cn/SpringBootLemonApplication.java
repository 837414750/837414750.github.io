package com.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cn.**.mapper")
//@ComponentScan("com.cn")
public class SpringBootLemonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLemonApplication.class, args);
	}
}
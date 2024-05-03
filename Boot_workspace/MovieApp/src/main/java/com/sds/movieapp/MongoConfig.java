package com.sds.movieapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

// Spring의 bean 설정 xml을 대신
// MongoDB에서는 Spring Mybatis에서처럼 CRUD를 수행하는 객체인 ~Template을 지원
// MongoTemplate 객체를 bean으로 등록하여 DAO에서 주입받아 사용하자

@Configuration
public class MongoConfig {

	// <bean id="mongoClient" class="MongoClient"> </bean> 	   : DB 접속 객체
	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://test:1234@localhost:27017"); // 접속 문자열
	}
	
	// <bean id="mongoTemplate" class="MongoTemplate"> </bean> : CRUD 수행 객체
	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "movie");
	}
		
}

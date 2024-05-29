package com.sds.recommendapp;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;

// Spring 3.0부터 이미 Spring 설정 xml을 대신할 어노테이션을 지원하였음
// @Configuration : Spring bin 설정 xml을 대신함

@Configuration
public class AppConfig {
	
	// 	<!-- 영화진흥위원회 API key -->
	//  <bean id="key" class="java.lang.String">
	//  	<constructor-arg value="30f984da944ad5a0b9051f9c46d04d27"/>
	//  </bean>
	
	@Bean
	public String key() {
		return "30f984da944ad5a0b9051f9c46d04d27";
	}
	
	//  <bean id="kobisOpenAPIRestService" class="kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService">
	//  	<constructor-arg ref="key"/>
	//  </bean>
	
	@Bean
	public KobisOpenAPIRestService kobisOpenAPIRestService(String key) {
		return new KobisOpenAPIRestService(key);
	}
	
	// Komoran
	@Bean
	public Komoran komoran() {
		// 설정 파일 위치
		String modelPath = null;
		try {
			modelPath = new ClassPathResource("model_light").getFile().getAbsolutePath();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Komoran(modelPath);
	}
}
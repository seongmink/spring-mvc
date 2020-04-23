package com.ksm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import com.ksm.beans.DataBean1;
import com.ksm.beans.DataBean2;

// 프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class RootAppContext {

	@Bean
	@ApplicationScope
	public DataBean1 applicationBean1() {
		return new DataBean1();
	}
	
	@Bean("applicationBean2")
	@ApplicationScope
	public DataBean2 applicationBean2() {
		return new DataBean2();
	}
}

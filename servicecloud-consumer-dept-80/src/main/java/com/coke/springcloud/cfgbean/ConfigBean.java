package com.coke.springcloud.cfgbean;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class ConfigBean {
	
	/**
	 * 默认采用轮训算法
	 * @return
	 */
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	
	/**
	 * 将其改为随机算法
	 * @return
	 */
	@Bean
	public IRule myRule(){
		return new RandomRule(); 
	}
}

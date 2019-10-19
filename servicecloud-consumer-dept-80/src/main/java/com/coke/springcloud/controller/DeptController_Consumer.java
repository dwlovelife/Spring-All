package com.coke.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.coke.springcloud.entities.Dept;

@Controller
public class DeptController_Consumer {
	
	private static final String REST_URL_PREFIX = "http://SERVICECLOUD-DEPT";
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 使用restTemplate访问restful非常的简单
	 * (url,requestMap,ResponseBean.class) 这三个参数分别代表
	 */
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(Dept dept){
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}")
	@ResponseBody
	public Dept get(@PathVariable("id") Long id){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
	}
	
	@ResponseBody
	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
	}
	
	@RequestMapping(value = "consumer/dept/discovery", method = RequestMethod.GET)
	@ResponseBody
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery", Object.class);

	}
}

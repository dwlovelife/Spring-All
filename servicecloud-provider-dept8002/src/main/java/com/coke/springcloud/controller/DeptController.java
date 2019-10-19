package com.coke.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coke.springcloud.service.DeptService;
import com.dingwei.springcloud.entities.Dept;

@RestController
public class DeptController {

	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add() {
		Dept dept = new Dept();
		dept.setDname("xxxxx");
		return deptService.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return deptService.list();
	}
	
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("*********" + list);

		List<ServiceInstance> serList = client
				.getInstances("SERVICECLOUD-DEPT");
		for (ServiceInstance element : serList) {
			System.out.println(element.getServiceId() + "\t"
					+ element.getHost() + "\t" + element.getPort() + "\t"
					+ element.getUri());
		}
		return this.client;
	}
	
}

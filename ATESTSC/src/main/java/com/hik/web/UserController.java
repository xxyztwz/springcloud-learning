package com.hik.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hik.domain.User;
import com.hik.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DiscoveryClient client;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Long add(@RequestParam String name, @RequestParam Integer age) {
		ServiceInstance instance = client.getLocalServiceInstance();
		User user = userService.add(name,age);
		logger.info("/add, host:" + instance.getHost() + 
				", service_id:" + instance.getServiceId() + ", name:" + name+ ", age:" + age);
		return user.getId();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAll(){
		return userService.getAll();
	}
}
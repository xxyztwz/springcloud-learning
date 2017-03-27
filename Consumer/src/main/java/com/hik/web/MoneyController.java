package com.hik.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hik.domain.Money;
import com.hik.service.MoneyService;

@RestController
@RequestMapping("/money")
public class MoneyController {
	private final Logger logger = Logger.getLogger(getClass());
	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private MoneyService moneyService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Money get(){
		ServiceInstance instance = client.getLocalServiceInstance();
		logger.info("/get, host:" + instance.getHost() + 
				", service_id:" + instance.getServiceId());
		return moneyService.update(100L, 555L);
	}
	
	
}

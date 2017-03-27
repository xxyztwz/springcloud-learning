package com.hik.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hik.domain.User;
import com.hik.service.client.fallback.ComputeClientFallback;

@FeignClient(value="compute-service",fallback=ComputeClientFallback.class)
public interface ComputeClient {

	@RequestMapping(value="/user/{id}",method = RequestMethod.PUT)
	User modify(@PathVariable("id") Long id,@RequestBody User user);

	@RequestMapping(value="/user/{id}",method = RequestMethod.DELETE)
	User delete(@PathVariable("id") Long id);
}

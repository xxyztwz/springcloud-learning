package com.hik.service.client.fallback;

import org.springframework.stereotype.Component;

import com.hik.domain.User;
import com.hik.service.client.ComputeClient;

@Component
public class ComputeClientFallback implements ComputeClient {

	@Override
	public User modify(Long id, User user) {
		return null;
	}

	@Override
	public User delete(Long id) {
		return null;
	}

}

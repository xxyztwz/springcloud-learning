package com.hik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hik.domain.Money;
import com.hik.domain.MoneyRepository;
import com.hik.domain.User;
import com.hik.service.client.ComputeClient;

@Service
public class MoneyService {
	@Autowired
	private MoneyRepository moneyRepository;
	
	@Autowired
    private ComputeClient computeClient;
	
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.REPEATABLE_READ)
	public Money update(Long userid, Long value){
		Money m = moneyRepository.findByUserid(userid);
		if(null!=m){
			m.setValue(value);
		}else{
			m = new Money(value, userid);
		}
		User user = computeClient.modify(userid, new User("t", 1));
		if(null==user){
			throw new RuntimeException("断路器发现异常");
		}
		return moneyRepository.save(m);
	}
}

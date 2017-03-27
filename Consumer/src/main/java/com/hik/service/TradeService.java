package com.hik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hik.domain.Money;
import com.hik.domain.MoneyRepository;

@Service
public class TradeService {
	@Autowired
	private MoneyRepository moneyRepository;
	
	@Transactional(propagation=Propagation.SUPPORTS,isolation=Isolation.REPEATABLE_READ)
	public Money update(Long userid, Long value){
		Money m = moneyRepository.findByUserid(userid);
		if(null!=m){
			m.setValue(value);
		}else{
			m = new Money(value, userid);
		}
		return moneyRepository.save(m);
	}
}

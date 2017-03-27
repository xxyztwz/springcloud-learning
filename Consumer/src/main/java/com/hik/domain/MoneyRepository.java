package com.hik.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Long>{
	Money findByUserid(Long userid);
}

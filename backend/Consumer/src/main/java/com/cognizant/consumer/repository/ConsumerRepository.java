package com.cognizant.consumer.repository;

import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.consumer.model.Consumer;

@Repository
@Transactional
@DynamicUpdate
public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
	Optional<Consumer> findById(Long consumerid);
	
	boolean existsById(Long id);
}

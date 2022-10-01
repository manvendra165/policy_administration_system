package com.cognizant.Policy.repository;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.Policy.model.ConsumerPolicy;

@Repository
@Transactional
@DynamicUpdate
public interface ConsumerPolicyRepository extends JpaRepository<ConsumerPolicy, Long> {

	Boolean existsByConsumerid(Long consumerid);

	ConsumerPolicy findByConsumerid(Long consumerid);

	ConsumerPolicy findByConsumeridAndBusinessid(Long consumerid, Long businessid);

}

package com.github.wanghongy132.carpark.m.infrastructure.persistence;

import com.github.wanghongy132.carpark.m.infrastructure.persistence.entity.ResponseMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author wanruxiuer
 */
@Repository
public interface ResponseMessageRepository extends JpaRepository<ResponseMessageEntity, Integer> {
    Optional<ResponseMessageEntity> findByMessageKey(String messageKey);
}

package com.test.demo.secondary.entity.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.test.demo.secondary.entity.TPayments;

public interface TPaymentsRepository extends JpaRepository<TPayments, Long>,JpaSpecificationExecutor<TPayments>{
    
    long countByorgGuid(String orgGuid);
    
    List<TPayments> findByorgGuidAndResourceTypeAndResourceName(String orgGuid,String type,String name,Sort sort);
    
    @Query(value = "select * from t_payments_201801 where org_guid=?1",nativeQuery = true)
    List<TPayments> getPayments(String orgGuid);
    
}

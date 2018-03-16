package com.test.demo.secondary.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.test.demo.secondary.entity.TAliyunAppInfo;

public interface AliyunAppInfoRepository extends JpaRepository<TAliyunAppInfo, Long>,JpaSpecificationExecutor<TAliyunAppInfo>{
    
    long countByappNameAndUserNameAndUserType(String appName,String userName,Integer userType);
}

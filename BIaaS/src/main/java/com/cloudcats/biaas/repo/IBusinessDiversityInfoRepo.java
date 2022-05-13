package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.BusinessDiversityInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBusinessDiversityInfoRepo extends CrudRepository<BusinessDiversityInfo, Integer> {
    @Override
    Optional<BusinessDiversityInfo> findById(Integer integer);
}

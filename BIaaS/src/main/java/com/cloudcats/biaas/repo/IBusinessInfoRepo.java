package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.BusinessInfo;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBusinessInfoRepo extends CrudRepository<BusinessInfo, Integer> {
    List<BusinessInfo> findAll();

    @Override
    Optional<BusinessInfo> findById(Integer integer);
}

package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.BusinessInfo;
import com.google.cloud.spanner.Key;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusinessInfoRepo extends SpannerRepository<BusinessInfo, Key> {
    List<BusinessInfo> findById(int businessId);
}

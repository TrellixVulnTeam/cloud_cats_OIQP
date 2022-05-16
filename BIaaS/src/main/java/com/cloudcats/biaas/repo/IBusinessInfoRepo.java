package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.BusinessInfo;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.cloud.gcp.data.spanner.repository.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusinessInfoRepo extends SpannerRepository<BusinessInfo, Integer> {
    List<BusinessInfo> findBusinessInfosByBusinessName(String businessName);
}

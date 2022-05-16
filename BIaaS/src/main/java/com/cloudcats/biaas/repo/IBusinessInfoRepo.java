package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.BusinessInfo;
import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.cloud.gcp.data.spanner.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBusinessInfoRepo extends SpannerRepository<BusinessInfo, Integer> {

    @Query("select * from BUSINESS_INFO bi where bi.BUS_NAME LIKE @businessName")
    List<BusinessInfo> findByBusinessName(@Param("businessName") String businessName);

}

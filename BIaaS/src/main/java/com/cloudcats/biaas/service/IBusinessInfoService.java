package com.cloudcats.biaas.service;


import com.cloudcats.biaas.dto.BusinessInfoPageRequest;
import com.cloudcats.biaas.dto.BusinessInfoPageResponse;
import com.cloudcats.biaas.entity.BusinessInfo;

import java.util.List;

public interface IBusinessInfoService {
    List<BusinessInfo> getBusinessesInfo();
    BusinessInfo getBusinessInfo(Integer businessId);
    BusinessInfoPageResponse getBusinessInfoPagination(BusinessInfoPageRequest businessInfoPaginationRequest);
}

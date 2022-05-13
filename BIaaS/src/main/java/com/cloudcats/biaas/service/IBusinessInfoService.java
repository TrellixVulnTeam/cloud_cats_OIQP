package com.cloudcats.biaas.service;


import com.cloudcats.biaas.entity.BusinessInfo;

import java.util.List;

public interface IBusinessInfoService {
    List<BusinessInfo> getBusinessesInfo();
    BusinessInfo getBusinessInfo(Integer businessId);
}

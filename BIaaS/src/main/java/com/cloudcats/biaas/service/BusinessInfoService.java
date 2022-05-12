package com.cloudcats.biaas.service;


import com.cloudcats.biaas.dto.BusinessInfoDto;

import java.util.List;

public interface BusinessInfoService {
    List<BusinessInfoDto> getBusinessesInfo();

    BusinessInfoDto getBusinessInfo(int businessId);
}

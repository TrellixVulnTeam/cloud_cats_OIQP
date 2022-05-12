package com.cloudcats.biaas.service.impl;

import com.cloudcats.biaas.dto.BusinessInfoDto;
import com.cloudcats.biaas.service.BusinessInfoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessInfoServiceImpl implements BusinessInfoService {

//    @Autowired
//    private BusinessInfoRepo businessInfoRepo;

    @Override
    public List<BusinessInfoDto> getBusinessesInfo() {
        List<BusinessInfoDto> businessesInfo = new ArrayList<>();
        BusinessInfoDto businessInfoDto = new BusinessInfoDto();
        businessInfoDto.setBusinessId(1);
        businessInfoDto.setBusinessName("sample business");
        businessesInfo.add(businessInfoDto);
        return businessesInfo;
    }

    @Override
    public BusinessInfoDto getBusinessInfo(int BusinessId) {
        return null;
    }
}

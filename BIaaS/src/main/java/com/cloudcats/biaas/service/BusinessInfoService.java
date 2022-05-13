package com.cloudcats.biaas.service;

import com.cloudcats.biaas.dto.BusinessInfoDto;
import com.cloudcats.biaas.entity.BusinessInfo;
import com.cloudcats.biaas.repo.IBusinessInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusinessInfoService implements IBusinessInfoService {

    @Autowired
    private IBusinessInfoRepo businessInfoRepo;

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
    public BusinessInfoDto getBusinessInfo(int businessId) {
        List<BusinessInfo> businessInfoList = businessInfoRepo.findById(businessId);
        return null;
    }
}

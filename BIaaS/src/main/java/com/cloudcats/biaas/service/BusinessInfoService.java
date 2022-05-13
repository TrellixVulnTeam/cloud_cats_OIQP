package com.cloudcats.biaas.service;

import com.cloudcats.biaas.entity.BusinessDiversityInfo;
import com.cloudcats.biaas.entity.BusinessInfo;
import com.cloudcats.biaas.repo.IBusinessDiversityInfoRepo;
import com.cloudcats.biaas.repo.IBusinessInfoRepo;
import com.cloudcats.biaas.repo.IMinorityOwnershipInfoRepo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessInfoService implements IBusinessInfoService {

    @Autowired
    private IBusinessInfoRepo businessInfoRepo;

    @Autowired
    private IBusinessDiversityInfoRepo businessDiversityInfoRepo;

    @Autowired
    private IMinorityOwnershipInfoRepo minorityOwnershipInfoRepo;

    @Override
    public List<BusinessInfo> getBusinessesInfo() {
        return businessInfoRepo.findAll();
    }

    @Override
    public BusinessInfo getBusinessInfo(Integer businessId) {
        Optional<BusinessInfo> businessInfoOptional = businessInfoRepo.findById(businessId);
        return businessInfoOptional.orElse(null);
    }
}

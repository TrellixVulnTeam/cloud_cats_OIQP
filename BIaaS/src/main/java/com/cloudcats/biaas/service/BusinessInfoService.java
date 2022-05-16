package com.cloudcats.biaas.service;

import com.cloudcats.biaas.dto.BusinessInfoPageRequest;
import com.cloudcats.biaas.dto.BusinessInfoPageResponse;
import com.cloudcats.biaas.entity.BusinessInfo;
import com.cloudcats.biaas.repo.IBusinessDiversityInfoRepo;
import com.cloudcats.biaas.repo.IBusinessInfoRepo;
import com.cloudcats.biaas.repo.IMinorityOwnershipInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
        return (List<BusinessInfo>) businessInfoRepo.findAll();
    }

    @Override
    public BusinessInfo getBusinessInfo(Integer businessId) {
        Optional<BusinessInfo> businessInfoOptional = businessInfoRepo.findById(businessId);
        return businessInfoOptional.orElse(null);
    }

    @Override
    public BusinessInfoPageResponse getBusinessInfoPagination(BusinessInfoPageRequest businessInfoPageRequest) {
        BusinessInfoPageResponse businessInfoPageResponse = new BusinessInfoPageResponse();

        int pageNumber = businessInfoPageRequest.getPageNumber() - 1;
        int pageSize = businessInfoPageRequest.getPageSize();
        String sortBy = businessInfoPageRequest.getSortBy();
        String searchBy = businessInfoPageRequest.getSearchBy();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));

        Page<BusinessInfo> businessInfoPage;

        if (searchBy != null && !searchBy.isEmpty()) {
            List<BusinessInfo> businessInfos = businessInfoRepo.findBusinessInfosByBusinessName(searchBy);
            businessInfoPage = new PageImpl<>(businessInfos, pageable, businessInfos.size());
        } else {
            businessInfoPage = businessInfoRepo.findAll(pageable);
        }

        if (businessInfoPage.hasContent()) {
            businessInfoPageResponse.setPageNumber(businessInfoPageRequest.getPageNumber());
            businessInfoPageResponse.setPageSize(pageSize);
            businessInfoPageResponse.setTotalPages(businessInfoPage.getTotalPages());
            businessInfoPageResponse.setSortBy(sortBy);
            businessInfoPageResponse.setTotalElements(businessInfoPage.getTotalElements());
            businessInfoPageResponse.setBusinessInfoList(businessInfoPage.getContent());
            return businessInfoPageResponse;
        }
        return businessInfoPageResponse;
    }

}

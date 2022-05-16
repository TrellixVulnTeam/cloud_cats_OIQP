package com.cloudcats.biaas.dto;

import com.cloudcats.biaas.entity.BusinessInfo;
import lombok.Data;

import java.util.List;

@Data
public class BusinessInfoPageResponse {
    int pageNumber;
    int pageSize;
    long totalPages;
    long totalElements;
    String sortBy;
    String searchBy;
    List<BusinessInfo> businessInfoList;
}

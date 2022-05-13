package com.cloudcats.biaas.dto;

import lombok.Data;

@Data
public class BusinessInfoPageRequest {
    int pageNumber = 1;
    int pageSize = 100;
    String sortBy = "businessId";
}

package com.cloudcats.biaas.dto;

import lombok.Data;

@Data
public class BusinessInfoPageRequest {
    int pageNumber;
    int pageSize;
    String sortBy;
}

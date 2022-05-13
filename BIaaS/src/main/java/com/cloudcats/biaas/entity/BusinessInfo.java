package com.cloudcats.biaas.entity;


import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import java.util.List;


@Table(name = "BUSINESS_INFO")
@Data
public class BusinessInfo {
    @PrimaryKey
    @Column(name = "BUS_ID")
    private Integer businessId;

    @Column(name = "BUS_FEIN")
    private String businessFEIN;

    @Column(name = "BUS_NAME")
    private String businessName;

    @Column(name = "BUS_STATE")
    private String businessState;

    @Column(name = "OWNERS")
    private String businessOwners;

    @Interleaved
    private List<BusinessDiversityInfo> businessDiversityInfo;
}

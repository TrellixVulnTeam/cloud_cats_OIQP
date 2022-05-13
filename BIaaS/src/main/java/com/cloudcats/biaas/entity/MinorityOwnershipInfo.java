package com.cloudcats.biaas.entity;

import lombok.Data;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

@Table(name = "MINORITY_OWNERSHIP_INFO")
@Data
public class MinorityOwnershipInfo {
    @Column(name = "BUS_ID")
    private Integer businessId;
    @Column(name = "AFRICAN_AMERICAN_OWNED")
    private Boolean africanAmericanOwned;
    @Column(name = "ASIAN_OWNED")
    private Boolean asianOwned;
    @Column(name = "HISPANIC_OWNED")
    private Boolean hispanicOwned;
    @Column(name = "NATIVE_AMERICAN_OWNED")
    private Boolean nativeAmericanOwned;
    @Column(name = "OTHER_MINORITY_OWNED")
    private Boolean otherMinorityOwned;
    @Column(name = "PACIFIC_ISLANDER_OWNED")
    private Boolean pacificIslanderOwned;
}

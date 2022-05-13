/**
 * 
 */
package cats.cloud.dataingestion.entity;

import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import lombok.Data;

/**
 * @author viral
 *
 */
@Data
@Table(name = "BUSINESS_DIVERSITY_INFO")
public class BizDiversityInfo {
    @PrimaryKey
    @Column(name = "BUS_ID")
	private Integer busId;
    @PrimaryKey(keyOrder = 2)
    @Column(name = "BUS_DIV_ID")
    private Integer businessDiversityId;
    @Column(name = "MINORITY_OWNED")
	private Boolean isMinorityOwned;
    @Column(name = "SMALL_BUSINESS")
	private Boolean isSmallBusiness;
    @Column(name = "WOMEN_OWNED")
	private Boolean isWomenOwned;
}

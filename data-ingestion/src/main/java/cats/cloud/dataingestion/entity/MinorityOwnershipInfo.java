/**
 * 
 */
package cats.cloud.dataingestion.entity;

import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import lombok.Data;

/**
 * @author viral
 *
 */
@Data
@Table(name = "MINORITY_OWNERSHIP_INFO")
public class MinorityOwnershipInfo {
	@Column(name = "BUS_ID")
	private Integer busId;
	@Column(name = "AFRICAN_AMERICAN_OWNED")
	private Boolean africanAmerican;
	@Column(name = "ASIAN_OWNED")
	private Boolean asian;
	@Column(name = "HISPANIC_OWNED")
	private Boolean hispanic;
	@Column(name = "NATIVE_AMERICAN_OWNED")
	private Boolean nativeAmerican;
	@Column(name = "PACIFIC_ISLANDER_OWNED")
	private Boolean pacificIslander;
	@Column(name = "OTHER_MINORITY_OWNED")
	private Boolean otherMinority;	
}

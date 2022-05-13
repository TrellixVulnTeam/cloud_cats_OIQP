/**
 * 
 */
package cats.cloud.dataingestion.entity;

import java.util.List;

import org.springframework.cloud.gcp.data.spanner.core.mapping.Column;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Interleaved;
import org.springframework.cloud.gcp.data.spanner.core.mapping.PrimaryKey;
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table;

import lombok.Data;

/**
 * @author viral
 *
 */
@Data
@Table(name = "BUSINESS_INFO")
public class BizInfo {
    @PrimaryKey
    @Column(name = "BUS_ID")
	private Integer busId;
    @Column(name = "BUS_FEIN")
	private String fein;
    @Column(name = "BUS_NAME")
	private String busName;
    @Column(name = "BUS_STATE")
	private String busState;
    @Column(name = "OWNERS")
	private String owners;
    
    @Interleaved
    private List<BizDiversityInfo> bizDiversityInfo;
}

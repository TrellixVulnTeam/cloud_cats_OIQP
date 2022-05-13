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
@Table(name = "sequences")
public class Sequences {
    @PrimaryKey
    @Column(name = "seq_name")
	private String seqName;
    @Column(name = "next_value")
	private Integer seqNextValue;
}

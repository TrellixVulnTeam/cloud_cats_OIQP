/**
 * 
 */
package cats.cloud.dataingestion.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author viral
 *
 */
@Data
public class CTBiz {
	@JsonProperty("name")
	private String vendorName;
	@JsonProperty("state_or_territory_formation")
	private String state;
	@JsonProperty("woman_owned_organization")
	private Boolean isWomenOwned;
	@JsonProperty("veteran_owned_organization")
	private Boolean isVeterannOwned;
	@JsonProperty("minority_owned_organization")
	private Boolean isMinorityOwned;
}

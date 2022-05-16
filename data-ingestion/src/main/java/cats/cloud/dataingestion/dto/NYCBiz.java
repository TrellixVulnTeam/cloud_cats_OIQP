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
public class NYCBiz {
	@JsonProperty("vendor_formal_name")
	private String vendorFormalName;
	@JsonProperty("contact_name")
	private String contactName;
	private String ethnicity;
	private String state;
	private String certification;
}

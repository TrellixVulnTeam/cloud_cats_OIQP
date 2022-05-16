/**
 * 
 */
package cats.cloud.dataingestion.service;

/**
 * @author viral
 *
 */
public interface DataIngestionService {
	/** 
	 * Saves NYC business data to the system
	 */
	public void saveNYCData();
	/** 
	 * Saves Connecticut business data to the system
	 */
	public void saveCTData();
}

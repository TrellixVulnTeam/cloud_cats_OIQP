/**
 * 
 */
package cats.cloud.dataingestion.repo;

import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

import cats.cloud.dataingestion.entity.MinorityOwnershipInfo;

/**
 * @author viral
 *
 */
@Repository
public interface MinorityOwnershipInfoRepo extends SpannerRepository<MinorityOwnershipInfo, Integer>{
}

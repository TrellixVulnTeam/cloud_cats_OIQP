/**
 * 
 */
package cats.cloud.dataingestion.repo;

import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;
import org.springframework.stereotype.Repository;

import cats.cloud.dataingestion.entity.BizDiversityInfo;

/**
 * @author viral
 *
 */
@Repository
public interface BizDiversityInfoRepo extends SpannerRepository<BizDiversityInfo, Integer>{
}

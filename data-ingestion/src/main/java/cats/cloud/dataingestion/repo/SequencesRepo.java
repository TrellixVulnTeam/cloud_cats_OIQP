/**
 * 
 */
package cats.cloud.dataingestion.repo;

import org.springframework.cloud.gcp.data.spanner.repository.SpannerRepository;

import cats.cloud.dataingestion.entity.Sequences;

/**
 * @author viral
 *
 */
public interface SequencesRepo extends SpannerRepository<Sequences, String>{
}

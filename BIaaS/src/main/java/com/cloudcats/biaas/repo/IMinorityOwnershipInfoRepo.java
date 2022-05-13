package com.cloudcats.biaas.repo;

import com.cloudcats.biaas.entity.MinorityOwnershipInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMinorityOwnershipInfoRepo extends CrudRepository<MinorityOwnershipInfo, Integer> {
    @Override
    Optional<MinorityOwnershipInfo> findById(Integer integer);
}

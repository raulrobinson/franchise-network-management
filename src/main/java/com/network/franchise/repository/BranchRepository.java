package com.network.franchise.repository;

import com.network.franchise.entity.Branch;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BranchRepository extends ReactiveCrudRepository<Branch, Long> {
    Flux<Branch> findByFranchiseId(Long franchiseId);
}

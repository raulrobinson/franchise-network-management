package com.network.franchise.repository;

import com.network.franchise.entity.Franchise;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FranchiseRepository extends ReactiveCrudRepository<Franchise, Long> {}

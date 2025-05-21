package com.network.franchise.repository;

import com.network.franchise.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Long> {
    Flux<Product> findByBranchId(Long branchId);
    Mono<Void> deleteByBranchIdAndId(Long branchId, Long id);
    Mono<Product> findTopByBranchIdOrderByStockDesc(Long branchId);
}

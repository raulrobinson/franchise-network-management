package com.network.franchise.handler;

import com.network.franchise.entity.Branch;
import com.network.franchise.entity.Franchise;
import com.network.franchise.entity.Product;
import com.network.franchise.repository.BranchRepository;
import com.network.franchise.repository.FranchiseRepository;
import com.network.franchise.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {
    private final FranchiseRepository franchiseRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    public Mono<ServerResponse> createFranchise(ServerRequest request) {
        return request.bodyToMono(Franchise.class)
                .flatMap(franchiseRepository::save)
                .flatMap(franchise -> ServerResponse.ok().bodyValue(franchise));
    }

    public Mono<ServerResponse> addBranch(ServerRequest request) {
        Long franchiseId = Long.parseLong(request.pathVariable("franchiseId"));
        return request.bodyToMono(Branch.class)
                .map(branch -> branch.toBuilder().franchiseId(franchiseId).build())
                .flatMap(branchRepository::save)
                .flatMap(branch -> ServerResponse.ok().bodyValue(branch));
    }

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        Long branchId = Long.parseLong(request.pathVariable("branchId"));
        return request.bodyToMono(Product.class)
                .map(product -> product.toBuilder().branchId(branchId).build())
                .flatMap(productRepository::save)
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> deleteProduct(ServerRequest request) {
        Long branchId = Long.parseLong(request.pathVariable("branchId"));
        Long productId = Long.parseLong(request.pathVariable("productId"));
        return productRepository.deleteByBranchIdAndId(branchId, productId)
                .then(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> updateStock(ServerRequest request) {
        Long productId = Long.parseLong(request.pathVariable("productId"));
        return productRepository.findById(productId)
                .zipWith(request.bodyToMono(Product.class))
                .map(tuple -> {
                    Product existing = tuple.getT1();
                    int newStock = tuple.getT2().getStock();
                    existing.setStock(newStock);
                    return existing;
                })
                .flatMap(productRepository::save)
                .flatMap(product -> ServerResponse.ok().bodyValue(product));
    }

    public Mono<ServerResponse> getTopProductsPerBranch(ServerRequest request) {
        Long franchiseId = Long.parseLong(request.pathVariable("franchiseId"));
        return branchRepository.findByFranchiseId(franchiseId)
                .flatMap(branch -> productRepository.findTopByBranchIdOrderByStockDesc(branch.getId())
                        .map(product -> Map.of("branch", branch, "product", product)))
                .collectList()
                .flatMap(results -> ServerResponse.ok().bodyValue(results));
    }
}
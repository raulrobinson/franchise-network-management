package com.network.franchise.router;

import com.network.franchise.handler.FranchiseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FranchiseRouter {
    @Bean
    public RouterFunction<ServerResponse> franchiseRoutes(FranchiseHandler handler) {
        return RouterFunctions.route()
                .POST("/franchises", handler::createFranchise)
                .POST("/franchises/{franchiseId}/branches", handler::addBranch)
                .POST("/branches/{branchId}/products", handler::addProduct)
                .DELETE("/branches/{branchId}/products/{productId}", handler::deleteProduct)
                .PUT("/products/{productId}/stock", handler::updateStock)
                .GET("/franchises/{franchiseId}/top-products", handler::getTopProductsPerBranch)
                .build();
    }
}


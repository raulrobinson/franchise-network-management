package com.network.franchise.application.config;

import com.network.franchise.domain.api.AppPersistenceAdapterPort;
import com.network.franchise.domain.usecase.command.*;
import com.network.franchise.domain.usecase.queries.GetTopProductsPerBranchUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing
public class UseCaseConfig {

    @Bean
    public CreateFranchiseUseCase createFranchiseUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new CreateFranchiseUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public AddBranchUseCase addBranchUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new AddBranchUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public AddProductUseCase addProductUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new AddProductUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new DeleteProductUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public UpdateStockUseCase updateStockUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new UpdateStockUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public GetTopProductsPerBranchUseCase getTopProductsPerBranchUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new GetTopProductsPerBranchUseCase(appPersistenceAdapterPort);
    }

    @Bean
    public UpdateFranchiseNameUseCase updateFranchiseNameUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort) {
        return new UpdateFranchiseNameUseCase(appPersistenceAdapterPort);
    }
}

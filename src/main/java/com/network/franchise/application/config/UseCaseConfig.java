package com.network.franchise.application.config;

import com.network.franchise.domain.api.AppPersistenceAdapterPort;
import com.network.franchise.domain.mapper.BranchesDomainMapper;
import com.network.franchise.domain.mapper.FranchiseDomainMapper;
import com.network.franchise.domain.usecase.command.AddBranchUseCase;
import com.network.franchise.domain.usecase.command.CreateFranchiseUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateFranchiseUseCase createFranchiseUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort,
                                                         FranchiseDomainMapper mapper) {
        return new CreateFranchiseUseCase(appPersistenceAdapterPort, mapper);
    }

    @Bean
    public AddBranchUseCase addBranchUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort,
                                             BranchesDomainMapper mapper) {
        return new AddBranchUseCase(appPersistenceAdapterPort, mapper);
    }
}

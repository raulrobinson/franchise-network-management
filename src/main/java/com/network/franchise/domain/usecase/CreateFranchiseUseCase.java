package com.network.franchise.domain.usecase;

import com.network.franchise.domain.api.FranchisePersistenceAdapterPort;
import com.network.franchise.domain.common.enums.TechnicalMessage;
import com.network.franchise.domain.common.exceptions.BusinessException;
import com.network.franchise.domain.common.exceptions.DuplicateException;
import com.network.franchise.domain.mapper.FranchiseDomainMapper;
import com.network.franchise.domain.model.Franchise;
import com.network.franchise.domain.spi.CreateFranchiseServicePort;
import reactor.core.publisher.Mono;

public class CreateFranchiseUseCase implements CreateFranchiseServicePort {

    private final FranchisePersistenceAdapterPort franchisePersistenceAdapterPort;
    private final FranchiseDomainMapper mapper;

    public CreateFranchiseUseCase(FranchisePersistenceAdapterPort franchisePersistenceAdapterPort, FranchiseDomainMapper mapper) {
        this.franchisePersistenceAdapterPort = franchisePersistenceAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public Mono<Franchise> createTechnology(Franchise request) {
        return franchisePersistenceAdapterPort.existsByName(request.getName())
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicateException(TechnicalMessage.ALREADY_EXISTS));
                    return franchisePersistenceAdapterPort.createFranchise(mapper.toEntityFromDomainFranchise(request))
                            .map(mapper::toDomainFromFranchiseEntity);
                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }
}

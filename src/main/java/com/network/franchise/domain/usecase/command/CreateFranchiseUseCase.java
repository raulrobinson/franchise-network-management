package com.network.franchise.domain.usecase.command;

import com.network.franchise.domain.api.AppPersistenceAdapterPort;
import com.network.franchise.domain.common.enums.TechnicalMessage;
import com.network.franchise.domain.common.exceptions.BusinessException;
import com.network.franchise.domain.common.exceptions.DuplicateException;
import com.network.franchise.infrastructure.mapper.FranchiseDtoMapper;
import com.network.franchise.domain.model.Franchise;
import com.network.franchise.domain.spi.CreateFranchiseServicePort;
import com.network.franchise.domain.dto.response.CreateFranchiseResponseDto;
import reactor.core.publisher.Mono;

public class CreateFranchiseUseCase implements CreateFranchiseServicePort {

    private final AppPersistenceAdapterPort appPersistenceAdapterPort;
    private final FranchiseDtoMapper mapper;

    public CreateFranchiseUseCase(AppPersistenceAdapterPort appPersistenceAdapterPort, FranchiseDtoMapper mapper) {
        this.appPersistenceAdapterPort = appPersistenceAdapterPort;
        this.mapper = mapper;
    }

    // TODO: Cambiar el response a objeto de dominio FRANCHISE, por el mapper ir por fuera
    @Override
    public Mono<CreateFranchiseResponseDto> createTechnology(Franchise request) {
        if (request.getName() == null || request.getName().isBlank()) {
            return Mono.error(new BusinessException(TechnicalMessage.MISSING_REQUIRED_FIELD));
        }
        // TODO: ajustar para validacion sin Imperativa
        // Mono.just(request).filter(franchise -> franchise.getName() != null);
        return appPersistenceAdapterPort.existsFranchiseByName(request.getName())
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicateException(TechnicalMessage.ALREADY_EXISTS));
                    return appPersistenceAdapterPort.createFranchise(mapper.toEntityFromDomainFranchise(request))
                            .map(mapper::toDomainFromFranchiseEntity);
                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }
}

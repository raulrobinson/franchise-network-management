package com.network.franchise.domain.mapper;

import com.network.franchise.domain.model.Franchise;
import com.network.franchise.dto.request.CreateFranchiseRequestDto;
import com.network.franchise.infrastructure.adapters.persistence.entity.FranchiseEntity;
import org.springframework.stereotype.Component;

@Component
public class FranchiseDomainMapper {

    public Franchise toDomainFromFranchiseRequestDto(CreateFranchiseRequestDto entity) {
        if (entity == null) return null;
        return Franchise.builder()
                .name(entity.getName())
                .build();
    }

    public FranchiseEntity toEntityFromDomainFranchise(Franchise domain) {
        if (domain == null) return null;
        return FranchiseEntity.builder()
                .name(domain.getName())
                .build();
    }

    public Franchise toDomainFromFranchiseEntity(FranchiseEntity entity) {
        if (entity == null) return null;
        return Franchise.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }


}

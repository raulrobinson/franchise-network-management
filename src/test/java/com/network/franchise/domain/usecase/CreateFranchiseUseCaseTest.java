package com.network.franchise.domain.usecase;

import com.network.franchise.domain.api.AppPersistenceAdapterPort;
import com.network.franchise.domain.common.exceptions.BusinessException;
import com.network.franchise.domain.common.exceptions.DuplicateException;
import com.network.franchise.domain.model.Franchise;
import com.network.franchise.domain.usecase.command.CreateFranchiseUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateFranchiseUseCaseTest {

    private AppPersistenceAdapterPort appPersistenceAdapterPort;
    private CreateFranchiseUseCase useCase;

    @BeforeEach
    void setUp() {
        appPersistenceAdapterPort = mock(AppPersistenceAdapterPort.class);
        useCase = new CreateFranchiseUseCase(appPersistenceAdapterPort);
    }

    @Test
    void createTechnology_shouldReturnError_whenAlreadyExists() {
        // Arrange
        Franchise request = Franchise.builder().name("Franchise B").build();

        when(appPersistenceAdapterPort.existsFranchiseByName("Franchise B"))
                .thenReturn(Mono.just(true));

        // Act & Assert
        StepVerifier.create(useCase.createTechnology(request))
                .expectErrorMatches(throwable ->
                        throwable instanceof DuplicateException)
                .verify();

        verify(appPersistenceAdapterPort, never()).createFranchise(any());
    }

    @Test
    void createTechnology_shouldReturnError_whenExistsReturnsEmpty() {
        // Arrange
        Franchise request = Franchise.builder().name("Franchise C").build();

        when(appPersistenceAdapterPort.existsFranchiseByName("Franchise C")).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(useCase.createTechnology(request))
                .expectErrorMatches(throwable ->
                        throwable instanceof BusinessException)
                .verify();

        verify(appPersistenceAdapterPort, never()).createFranchise(any());
    }
}

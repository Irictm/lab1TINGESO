package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import fernandoIribarra.lab1TINGESO.repositories.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperationServiceTest {

    @Mock
    private OperationRepository operationRepository;

    @InjectMocks
    OperationService operationService = new OperationService();
    OperationEntity operation = new OperationEntity(1L,
            4,
            1L);

    @Test
    public void whenGetOperationById_thenOperation() {
        // Given
        // operation entity defined earlier
        when(operationRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(operation));

        // When
        OperationEntity foundOperation = operationService.getOperationById(operation.getId());

        // Then
        assertThat(foundOperation).isEqualTo(operation);
    }

    @Test
    public void whenCalculateBaseCost_then210_000L() {
        // Given
        // operation entity defined earlier

        // When
        Long foundBaseCost = operationService.calculateBaseCost(operation, "Diesel");

        // Then
        assertThat(foundBaseCost).isEqualTo(210_000L);
    }

    @Test
    public void whenCalculateTotalRepairBaseCost_then420_000L() {
        // Given
        // operation entity defined earlier
        OperationEntity operation1 = new OperationEntity(2L,
                4,
                1L);
        OperationEntity operation2 = new OperationEntity(3L,
                4,
                1L);
        List<OperationEntity> operations = List.of(operation1, operation2);
        when(operationRepository.findOperationsByRepair(Mockito.anyLong())).thenReturn(operations);

        // When
        Long foundBaseCost = operationService.calculateTotalRepairBaseCost(1L, "Diesel");

        // Then
        assertThat(foundBaseCost).isEqualTo(420_000L);
    }

    @Test
    public void whenGetOperationsByRepair_thenOperations() {
        // Given
        // operation entity defined earlier
        OperationEntity operation2 = new OperationEntity(2L,
                5,
                1L);
        List<OperationEntity> operations = List.of(operation, operation2);
        when(operationRepository.findOperationsByRepair(Mockito.anyLong())).thenReturn(operations);

        // When
        List<OperationEntity> foundOperations = operationService.getOperationsByRepair(1L);

        // Then
        assertThat(foundOperations).isEqualTo(operations);
    }

    @Test
    public void whenSaveOperation_thenOperation() {
        // Given
        // operation entity defined earlier
        when(operationRepository.save(Mockito.any(OperationEntity.class))).thenReturn(operation);

        // When
        OperationEntity savedOperation = operationService.saveOperation(operation);

        // Then
        assertThat(savedOperation).isEqualTo(operation);
    }

    @Test
    public void whenUpdateOperation_thenOperation() {
        // Given
        // operation entity defined earlier
        when(operationRepository.save(Mockito.any(OperationEntity.class))).thenReturn(operation);

        // When
        OperationEntity updatedOperation = operationService.updateOperation(operation);

        // Then
        assertThat(updatedOperation).isEqualTo(operation);
    }

    @Test
    public void whenDeleteOperationsWithRepair_thenNull() throws Exception {
        // Given
        // operation entity defined earlier

        // When
        boolean response = operationService.deleteOperationsWithRepair(operation.getId_repair());

        // Then
        assertThat(response).isEqualTo(true);
    }

    @Test
    public void whenDeleteOperation_thenNull() throws Exception {
        // Given
        // operation entity defined earlier

        // When
        boolean response = operationService.deleteOperation(operation.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }
}
package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import fernandoIribarra.lab1TINGESO.repositories.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        OperationEntity updatedOperation = operationService.saveOperation(operation);

        // Then
        assertThat(updatedOperation).isEqualTo(operation);
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
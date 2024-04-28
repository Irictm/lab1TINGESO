package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class OperationServiceTest {

    OperationService operationService = new OperationService();
    OperationEntity operation = new OperationEntity(1L,
            4,
            1L);

    @Test
    public void whenGetOperationById_thenOperation() {
        // Given
        // operation entity defined earlier

        // When
        given(operationService.getOperationById(1L)).willReturn(operation);

        // Then
        assertThat(operation.getId()).isEqualTo(1L);
    }

    @Test
    public void whenSaveOperation_thenOperation() {
        // Given
        // operation entity defined earlier

        // When
        given(operationService.saveOperation(operation)).willReturn(operation);

        // Then
        assertThat(operation.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdateOperation_thenOperation() {
        // Given
        // operation entity defined earlier

        // When
        given(operationService.updateOperation(operation)).willReturn(operation);

        // Then
        assertThat(operation.getId()).isEqualTo(1L);
    }

    @Test
    public void whenDeleteOperation_thenNull() throws Exception {
        // Given
        // operation entity defined earlier

        // When
        given(operationService.deleteOperation(operation.getId())).willReturn(true);

        // Then
        assertThat(operation.getId()).isEqualTo(1L);
    }
}
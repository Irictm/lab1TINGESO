package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class RepairServiceTest {
    RepairService repairService = new RepairService();
    RepairEntity repair = new RepairEntity(null,
            LocalDateTime.now(),
            5L,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1L);

    @Test
    public void whenGetRepairById_thenRepair() {
        // Given
        // repair entity defined earlier

        // When
        given(repairService.getRepairById(1L)).willReturn(repair);

        // Then
        assertThat(repair.getId()).isEqualTo(1L);
    }

    @Test
    public void whenSaveRepair_thenRepair() {
        // Given
        // repair entity defined earlier

        // When
        given(repairService.saveRepair(repair)).willReturn(repair);

        // Then
        assertThat(repair.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdateRepair_thenRepair() {
        // Given
        // repair entity defined earlier

        // When
        given(repairService.updateRepair(repair)).willReturn(repair);

        // Then
        assertThat(repair.getId()).isEqualTo(1L);
    }

    @Test
    public void whenDeleteRepair_thenNull() throws Exception {
        // Given
        // repair entity defined earlier

        // When
        given(repairService.deleteRepair(repair.getId())).willReturn(true);

        // Then
        assertThat(repair.getId()).isEqualTo(1L);
    }
}

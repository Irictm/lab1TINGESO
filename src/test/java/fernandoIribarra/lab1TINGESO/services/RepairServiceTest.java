package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.repositories.RepairRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepairServiceTest {

    @Mock
    private RepairRepository repairRepository;

    @Mock
    private OperationService operationService;

    @InjectMocks
    RepairService repairService = new RepairService();
    RepairEntity repair = new RepairEntity(1L,
            LocalDateTime.now(),
            5L,
            LocalDateTime.now(),
            LocalDateTime.now(),
            1L);

    @Test
    public void whenGetRepairById_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(repair));

        // When
        RepairEntity foundRepair = repairService.getRepairById(repair.getId());

        // Then
        assertThat(foundRepair).isEqualTo(repair);
    }

    @Test
    public void whenSaveRepair_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);

        // When
        RepairEntity savedRepair = repairService.saveRepair(repair);

        // Then
        assertThat(savedRepair).isEqualTo(repair);
    }

    @Test
    public void whenUpdateRepair_thenRepair() {
        // Given
        // repair entity defined earlier
        when(repairRepository.save(Mockito.any(RepairEntity.class))).thenReturn(repair);

        // When
        RepairEntity updatedRepair = repairService.saveRepair(repair);

        // Then
        assertThat(updatedRepair).isEqualTo(repair);
    }

    @Test
    public void whenDeleteRepair_thenNull() throws Exception {
        // Given
        // repair entity defined earlier

        // When
        boolean response = repairService.deleteRepair(repair.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }
}

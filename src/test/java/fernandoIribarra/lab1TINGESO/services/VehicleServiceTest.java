package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private RepairService repairService;

    @InjectMocks
    VehicleService vehicleService = new VehicleService();
    VehicleEntity vehicle = new VehicleEntity(1L,
            "09-32-LA",
            "Toyota",
            "model1",
            "SUV",
            LocalDate.now(),
            "Diesel",
            4,
            4432L);

    @Test
    public void whenGetVehicleById_thenVehicle() {
        // Given
        // vehicle entity defined earlier
        when(vehicleRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(vehicle));

        // When
        VehicleEntity foundVehicle = vehicleService.getVehicleById(vehicle.getId());

        // Then
        assertThat(foundVehicle).isEqualTo(vehicle);
    }

    @Test
    public void whenSaveVehicle_thenVehicle() {
        // Given
        // vehicle entity defined earlier
        when(vehicleRepository.save(Mockito.any(VehicleEntity.class))).thenReturn(vehicle);

        // When
        VehicleEntity savedVehicle = vehicleService.saveVehicle(vehicle);

        // Then
        assertThat(savedVehicle).isEqualTo(vehicle);
    }

    @Test
    public void whenUpdateVehicle_thenVehicle() {
        // Given
        // vehicle entity defined earlier
        when(vehicleRepository.save(Mockito.any(VehicleEntity.class))).thenReturn(vehicle);

        // When
        VehicleEntity updatedVehicle = vehicleService.saveVehicle(vehicle);

        // Then
        assertThat(updatedVehicle).isEqualTo(vehicle);
    }

    @Test
    public void whenDeleteVehicle_thenNull() throws Exception {
        // Given
        // vehicle entity defined earlier

        // When
        boolean response = vehicleService.deleteVehicle(vehicle.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }
}

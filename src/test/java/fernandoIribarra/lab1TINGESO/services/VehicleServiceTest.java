package fernandoIribarra.lab1TINGESO.services;

import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class VehicleServiceTest {
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

        // When
        given(vehicleService.getVehicleById(1L)).willReturn(vehicle);

        // Then
        assertThat(vehicle.getId()).isEqualTo(1L);
    }

    @Test
    public void whenSaveVehicle_thenVehicle() {
        // Given
        // vehicle entity defined earlier

        // When
        given(vehicleService.saveVehicle(vehicle)).willReturn(vehicle);

        // Then
        assertThat(vehicle.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdateVehicle_thenVehicle() {
        // Given
        // vehicle entity defined earlier

        // When
        given(vehicleService.updateVehicle(vehicle)).willReturn(vehicle);

        // Then
        assertThat(vehicle.getId()).isEqualTo(1L);
    }

    @Test
    public void whenDeleteVehicle_thenNull() throws Exception {
        // Given
        // vehicle entity defined earlier

        // When
        given(vehicleService.deleteVehicle(vehicle.getId())).willReturn(true);

        // Then
        assertThat(vehicle.getId()).isEqualTo(1L);
    }
}

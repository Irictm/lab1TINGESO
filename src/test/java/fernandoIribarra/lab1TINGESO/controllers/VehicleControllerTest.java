package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.VehicleEntity;
import fernandoIribarra.lab1TINGESO.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    public void getVehicleById_ShouldReturnVehicle() throws Exception {
        VehicleEntity vehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);
        given(vehicleService.getVehicleById(1L)).willReturn(vehicle);

        mockMvc.perform(get("/api/v1/vehicle/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void saveVehicle_ShouldReturnSavedEmployee() throws Exception {
        VehicleEntity savedVehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);

        given(vehicleService.saveVehicle(Mockito.any(VehicleEntity.class))).willReturn(savedVehicle);

        String vehicleJson = """
                    {
                        "patentNumber": "09-32-LA",
                        "brand": "Toyota",
                        "model": "model1",
                        "type": "SUV",
                        "fabricationDate": "2019-05-12",
                        "motorType": "Diesel",
                        "numberOfSeats": 4,
                        "mileage": 4432
                    }
                """;

        mockMvc.perform(post("/api/v1/vehicle/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void updateVehicle_ShouldReturnUpdatedVehicle() throws Exception {
        VehicleEntity updatedVehicle = new VehicleEntity(1L,
                "09-32-LA",
                "Toyota",
                "model1",
                "SUV",
                LocalDate.now(),
                "Diesel",
                4,
                4432L);

        given(vehicleService.updateVehicle(Mockito.any(VehicleEntity.class))).willReturn(updatedVehicle);

        String vehicleJson = """
                    {
                        "patentNumber": "09-32-LA",
                        "brand": "Toyota",
                        "model": "model1",
                        "type": "SUV",
                        "fabricationDate": "2019-05-12",
                        "motorType": "Diesel",
                        "numberOfSeats": 4,
                        "mileage": 4432
                    }
                """;


        mockMvc.perform(put("/api/v1/vehicle/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void deleteVehicle_ShouldReturn204() throws Exception {
        when(vehicleService.deleteVehicle(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/vehicle/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

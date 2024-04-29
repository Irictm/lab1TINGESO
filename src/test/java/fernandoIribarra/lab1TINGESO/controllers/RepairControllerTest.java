package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import fernandoIribarra.lab1TINGESO.services.RepairService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RepairController.class)
public class RepairControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepairService repairService;

    @Test
    public void getRepairById_ShouldReturnRepair() throws Exception {
        RepairEntity repair = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        given(repairService.getRepairById(1L)).willReturn(repair);

        mockMvc.perform(get("/api/v1/repair/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalAmount", is(5)));
    }

    @Test
    public void getAllRepairs_ShouldReturnRepairs() throws Exception {
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 6L, LocalDateTime.now(), LocalDateTime.now(),1L);
        given(repairService.getAllRepairs()).willReturn(List.of(repair1, repair2));

        mockMvc.perform(get("/api/v1/repair/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].totalAmount", is(5)))
                .andExpect(jsonPath("$[1].totalAmount", is(6)));
    }

    @Test
    public void getAllRepairsWithOpType_ShouldReturnRepairs() throws Exception {
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 6L, LocalDateTime.now(), LocalDateTime.now(),1L);
        given(repairService.getAllRepairsWithOperationType(Mockito.anyInt())).willReturn(List.of(repair1, repair2));

        mockMvc.perform(get("/api/v1/repair/operationtype/{typeOp}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].totalAmount", is(5)))
                .andExpect(jsonPath("$[1].totalAmount", is(6)));
    }

    @Test
    public void getCalculateRepairTotalAmount_ShouldReturnRepair() throws Exception {
        RepairEntity repair = new RepairEntity(null, LocalDateTime.now(), 500L, LocalDateTime.now(), LocalDateTime.now(),1L);
        given(repairService.getRepairById(Mockito.anyLong())).willReturn(repair);
        given(repairService.calculateTotalCost(Mockito.any(RepairEntity.class))).willReturn(repair);

        mockMvc.perform(get("/api/v1/repair/{id}/calculate", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalAmount", is(500)));
    }

    @Test
    public void saveRepair_ShouldReturnSavedEmployee() throws Exception {
        RepairEntity savedRepair = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);

        given(repairService.saveRepair(Mockito.any(RepairEntity.class))).willReturn(savedRepair);

        String repairJson = """
             {
                "dateOfAdmission": "2019-05-10 16:56:02",
                "totalAmount": 0,
                "dateOfRelease": "2019-05-12 15:00:04",
                "dateOfPickUp": "2019-05-12 17:21:42",
                "id_vehicle": 1
             }
                """;

        mockMvc.perform(post("/api/v1/repair/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount", is(5)));
    }

    @Test
    public void updateRepair_ShouldReturnUpdatedRepair() throws Exception {
        RepairEntity updatedRepair = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);

        given(repairService.updateRepair(Mockito.any(RepairEntity.class))).willReturn(updatedRepair);

        String repairJson = """
             {
                "dateOfAdmission": "2019-05-10 16:56:02",
                "totalAmount": 0,
                "dateOfRelease": "2019-05-12 15:00:04",
                "dateOfPickUp": "2019-05-12 17:21:42",
                "id_vehicle": 1
             }
                """;


        mockMvc.perform(put("/api/v1/repair/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(repairJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalAmount", is(5)));
    }

    @Test
    public void deleteRepair_ShouldReturn204() throws Exception {
        when(repairService.deleteRepair(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/repair/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
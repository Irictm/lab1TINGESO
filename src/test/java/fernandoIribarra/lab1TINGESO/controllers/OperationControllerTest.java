package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import fernandoIribarra.lab1TINGESO.services.OperationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OperationController.class)
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationService operationService;

    @Test
    public void getOperationById_ShouldReturnOperation() throws Exception {
        OperationEntity operation = new OperationEntity(1L,
                4,
                1L);
        given(operationService.getOperationById(1L)).willReturn(operation);

        mockMvc.perform(get("/api/v1/operation/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.type", is(4)));
    }

    @Test
    public void saveOperation_ShouldReturnSavedEmployee() throws Exception {
        OperationEntity savedOperation = new OperationEntity(1L,
                4,
                1L);

        given(operationService.saveOperation(Mockito.any(OperationEntity.class))).willReturn(savedOperation);

        String operationJson = """
            {
                "type": 4,
                "id_repair": 1
            }
            """;

        mockMvc.perform(post("/api/v1/operation/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(operationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(4)));
    }

    @Test
    public void updateOperation_ShouldReturnUpdatedOperation() throws Exception {
        OperationEntity updatedOperation = new OperationEntity(1L,
                4,
                1L);

        given(operationService.updateOperation(Mockito.any(OperationEntity.class))).willReturn(updatedOperation);

        String operationJson = """
            {
                "id": 1,
                "type": 4,
                "id_repair": 1
            }
            """;


        mockMvc.perform(put("/api/v1/operation/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(operationJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type", is(4)));
    }

    @Test
    public void deleteOperation_ShouldReturn204() throws Exception {
        when(operationService.deleteOperation(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/operation/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
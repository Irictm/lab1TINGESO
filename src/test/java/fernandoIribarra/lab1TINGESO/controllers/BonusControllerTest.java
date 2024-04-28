package fernandoIribarra.lab1TINGESO.controllers;

import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import fernandoIribarra.lab1TINGESO.services.BonusService;
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

@WebMvcTest(BonusController.class)
public class BonusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BonusService bonusService;

    @Test
    public void getBonusById_ShouldReturnBonus() throws Exception {
        BonusEntity bonus = new BonusEntity(1L,
                "Toyota",
                4,
                10000L);
        given(bonusService.getBonusById(1L)).willReturn(bonus);

        mockMvc.perform(get("/api/v1/bonus/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void saveBonus_ShouldReturnSavedEmployee() throws Exception {
        BonusEntity savedBonus = new BonusEntity(1L,
                "Toyota",
                4,
                10000L);

        given(bonusService.saveBonus(Mockito.any(BonusEntity.class))).willReturn(savedBonus);

        String bonusJson = """
            {
                "brand": "Toyota",
                "numberRemaining": 4,
                "amount": 10000
            }
            """;

        mockMvc.perform(post("/api/v1/bonus/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bonusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void updateBonus_ShouldReturnUpdatedBonus() throws Exception {
        BonusEntity updatedBonus = new BonusEntity(1L,
                "Toyota",
                4,
                10000L);

        given(bonusService.updateBonus(Mockito.any(BonusEntity.class))).willReturn(updatedBonus);

        String bonusJson = """
            {
                "id": 1,
                "brand": "Toyota",
                "numberRemaining": 4,
                "amount": 10000
            }
            """;


        mockMvc.perform(put("/api/v1/bonus/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bonusJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Toyota")));
    }

    @Test
    public void deleteBonus_ShouldReturn204() throws Exception {
        when(bonusService.deleteBonus(1L)).thenReturn(true); // Assuming the method returns a boolean

        mockMvc.perform(delete("/api/v1/bonus/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

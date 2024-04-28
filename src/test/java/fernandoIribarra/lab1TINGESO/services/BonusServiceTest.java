package fernandoIribarra.lab1TINGESO.services;


import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class BonusServiceTest {
    BonusService bonusService = new BonusService();
    BonusEntity bonus = new BonusEntity(1L,
            "Toyota",
            5,
            10000L);

    @Test
    public void whenGetBonusById_thenBonus() {
        // Given
        // bonus entity defined earlier

        // When
        given(bonusService.getBonusById(1L)).willReturn(bonus);

        // Then
        assertThat(bonus.getId()).isEqualTo(1L);
    }

    @Test
    public void whenSaveBonus_thenBonus() {
        // Given
        // bonus entity defined earlier

        // When
        given(bonusService.saveBonus(bonus)).willReturn(bonus);

        // Then
        assertThat(bonus.getId()).isEqualTo(1L);
    }

    @Test
    public void whenUpdateBonus_thenBonus() {
        // Given
        // bonus entity defined earlier

        // When
        given(bonusService.updateBonus(bonus)).willReturn(bonus);

        // Then
        assertThat(bonus.getId()).isEqualTo(1L);
    }

    @Test
    public void whenDeleteBonus_thenNull() throws Exception {
        // Given
        // bonus entity defined earlier

        // When
        given(bonusService.deleteBonus(bonus.getId())).willReturn(true);

        // Then
        assertThat(bonus.getId()).isEqualTo(1L);
    }
}

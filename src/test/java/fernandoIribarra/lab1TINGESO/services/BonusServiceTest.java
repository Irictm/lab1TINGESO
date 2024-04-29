package fernandoIribarra.lab1TINGESO.services;


import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import fernandoIribarra.lab1TINGESO.repositories.BonusRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BonusServiceTest {

    @Mock
    private BonusRepository bonusRepository;

    @InjectMocks
    BonusService bonusService = new BonusService();
    BonusEntity bonus = new BonusEntity(1L,
            "Toyota",
            5,
            10000L);

    @Test
    public void whenGetBonusById_thenBonus() {
        // Given
        // bonus entity defined earlier
        when(bonusRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(bonus));

        // When
        BonusEntity foundBonus = bonusService.getBonusById(bonus.getId());

        // Then
        assertThat(foundBonus).isEqualTo(bonus);
    }

    @Test
    public void whenSaveBonus_thenBonus() {
        // Given
        // bonus entity defined earlier
        when(bonusRepository.save(Mockito.any(BonusEntity.class))).thenReturn(bonus);

        // When
        BonusEntity savedBonus = bonusService.saveBonus(bonus);

        // Then
        assertThat(bonus).isEqualTo(savedBonus);
    }

    @Test
    public void whenUpdateBonus_thenBonus() {
        // Given
        // bonus entity defined earlier
        when(bonusRepository.save(Mockito.any(BonusEntity.class))).thenReturn(bonus);

        // When
        BonusEntity updatedBonus = bonusService.updateBonus(bonus);

        // Then
        assertThat(bonus).isEqualTo(updatedBonus);
    }

    @Test
    public void whenDeleteBonus_thenNull() throws Exception {
        // Given
        // bonus entity defined earlier

        // When
        boolean response = bonusService.deleteBonus(bonus.getId());

        // Then
        assertThat(response).isEqualTo(true);
    }
}

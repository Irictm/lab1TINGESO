package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.BonusEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class BonusRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  BonusRepository bonusRepository;

    @Test
    public void whenFindByBrand_thenReturnBonus() {
        // given
        BonusEntity bonus = new BonusEntity(null, "Toyota", 4, 10000L);
        entityManager.persistAndFlush(bonus);

        // when
        BonusEntity found = bonusRepository.findByBrand(bonus.getBrand());

        // then
        assertThat(found.getBrand()).isEqualTo(bonus.getBrand());
    }
}

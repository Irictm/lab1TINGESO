package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class RepairRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepairRepository repairRepository;

    @Test
    public void whenFindVehicleRepairs_thenReturnRepairs() {
        // given
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        entityManager.persist(repair1);
        entityManager.persist(repair2);
        entityManager.flush();

        // when
        List<RepairEntity> foundRepairs = repairRepository.findVehicleRepairs(1L);

        // then
        assertThat(foundRepairs).hasSize(2).extracting(RepairEntity::getTotalAmount).containsOnly(5L);
    }

    @Test
    public void whenFindRepairsWithOperation_thenReturnRepairs() {
        // given
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        entityManager.persist(repair1);
        entityManager.persist(repair2);
        entityManager.flush();

        // when
        List<RepairEntity> foundRepairs = repairRepository.findRepairsWithOperation(1);

        // then
        assertThat(foundRepairs).isNull();
    }
}

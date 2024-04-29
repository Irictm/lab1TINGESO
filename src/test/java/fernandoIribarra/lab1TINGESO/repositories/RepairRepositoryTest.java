package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
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
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 8L, LocalDateTime.now(), LocalDateTime.now(),8L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 9L, LocalDateTime.now(), LocalDateTime.now(),9L);
        entityManager.persist(repair1);
        entityManager.persist(repair2);
        entityManager.flush();

        // when
        List<RepairEntity> foundRepairs = repairRepository.findVehicleRepairs(8L);

        // then
        assertThat(foundRepairs).hasSize(1).extracting(RepairEntity::getTotalAmount).containsOnly(8L);
    }

    @Test
    public void whenFindRepairsWithOperation_thenReturnRepairs() {
        // given
        RepairEntity repair1 = new RepairEntity(null, LocalDateTime.now(), 5L, LocalDateTime.now(), LocalDateTime.now(),1L);
        RepairEntity repair2 = new RepairEntity(null, LocalDateTime.now(), 4L, LocalDateTime.now(), LocalDateTime.now(),1L);
        OperationEntity operation = new OperationEntity(null, 2, 3L);
        entityManager.persist(repair1);
        entityManager.persist(repair2);
        entityManager.persist(operation);
        entityManager.flush();

        // when
        List<RepairEntity> foundRepairs = repairRepository.findRepairsWithOperation(2);

        // then
        assertThat(foundRepairs).hasSize(1).extracting(RepairEntity::getTotalAmount).containsOnly(5L);
    }
}

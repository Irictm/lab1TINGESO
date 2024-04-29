package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class OperationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OperationRepository operationRepository;

    @Test
    public void whenFindOperationsByRepair_thenReturnOperations() {
        // given
        OperationEntity operation1 = new OperationEntity(null, 3, 1L);
        OperationEntity operation2 = new OperationEntity(null, 3, 1L);
        entityManager.persist(operation1);
        entityManager.persist(operation2);
        entityManager.flush();

        // when
        List<OperationEntity> foundOperations = operationRepository.findOperationsByRepair(1L);

        // then
        assertThat(foundOperations).hasSize(2).extracting(OperationEntity::getId_repair).containsOnly(1L);
    }

    @Test
    public void whenDeleteOperationsByRepair_thenReturnNull() {
        // given
        OperationEntity operation1 = new OperationEntity(null, 4, 1L);
        OperationEntity operation2 = new OperationEntity(null, 4, 1L);
        Long id1 = entityManager.persistAndGetId(operation1, Long.class);
        Long id2 = entityManager.persistAndGetId(operation2, Long.class);
        entityManager.flush();

        // when
        operationRepository.deleteOperationsByRepair(1L);
        entityManager.flush();
        entityManager.clear();

        // then
        assertThat(entityManager.find(OperationEntity.class, id1)).isNull();
        assertThat(entityManager.find(OperationEntity.class, id2)).isNull();
    }

}

package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<OperationEntity, Long> {
    @Query(value="SELECT * FROM operations WHERE id_repair = :id_repair", nativeQuery = true)
    public List<OperationEntity> findOperationsByRepair(@Param("id_repair") Long id_repair);
}

package fernandoIribarra.lab1TINGESO.repositories;

import fernandoIribarra.lab1TINGESO.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {

    @Query(value = "SELECT * FROM repairs WHERE id_vehicle = :id_vehicle", nativeQuery = true)
    public List<RepairEntity> findVehicleRepairs(@Param("id_vehicle") Long id_vehicle);

}

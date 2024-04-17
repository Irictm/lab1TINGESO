package fernandoIribarra.lab1TINGESO.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "repairs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    private LocalDateTime dateOfAdmission;
    private int type;
    private long currentVehicleMileage;
    private long totalAmount;
    private LocalDateTime dateOfRelease;
    private LocalDateTime dateOfPickUp;
    private long id_vehicle;
    private long id_receipt;
    private long id_bonus;
}

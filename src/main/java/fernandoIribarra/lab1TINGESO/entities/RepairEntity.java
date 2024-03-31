package fernandoIribarra.lab1TINGESO.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date dateOfAdmission;
    private String type;
    private long totalAmount;
    private Date dateOfRelease;
    private Date dateOfPickUp;
}

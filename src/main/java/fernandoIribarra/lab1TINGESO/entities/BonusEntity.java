package fernandoIribarra.lab1TINGESO.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bonus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    private String brand;
    private int numberRemaining;
    private long amount;
}

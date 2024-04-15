package fernandoIribarra.lab1TINGESO.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private long id;
    private String patentNumber;
    private String brand;
    private String model;
    private String type;
    private Date fabricationDate;
    private String motorType;
    private int numberOfSeats;
    private long mileage;
}

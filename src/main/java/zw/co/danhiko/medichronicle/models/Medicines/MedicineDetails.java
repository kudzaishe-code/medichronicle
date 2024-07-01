package zw.co.danhiko.medichronicle.models.Medicines;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "medicines")
public class MedicineDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

    @Transient // This field won't be persisted to the database
    private boolean provided;
}

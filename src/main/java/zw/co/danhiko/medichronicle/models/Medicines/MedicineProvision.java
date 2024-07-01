package zw.co.danhiko.medichronicle.models.Medicines;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "medicine_provisions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private MedicineDetails medicine;
    
    // Add other fields as needed, such as patient, provision date, etc.
}

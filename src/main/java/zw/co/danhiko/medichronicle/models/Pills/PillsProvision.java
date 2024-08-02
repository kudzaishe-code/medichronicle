package zw.co.danhiko.medichronicle.models.Pills;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "medicine_provisions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PillsProvision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private PillsDetails medicine;
    
    // Add other fields as needed, such as patient, provision date, etc.
}

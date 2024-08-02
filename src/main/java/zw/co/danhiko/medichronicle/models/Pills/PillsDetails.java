package zw.co.danhiko.medichronicle.models.Pills;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "medicines")
public class PillsDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String dosage;
    private String frequency;
//    @ManyToOne
//    @JoinColumn(name = "medical_records_id")
//    private MedicalRecords medicalRecords;


    @Transient // This field won't be persisted to the database
    private boolean provided;
}

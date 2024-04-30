package zw.co.danhiko.medichronicle.models.PrescriptionDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.Pharmacy.Pharmacy;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor


@Entity(name = "prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prescription;
    @OneToOne
    @JoinColumn(name = "medical_records_id")
    private MedicalRecords medicalRecords;
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

       private boolean medicationProvided;
       public void setMedicationRequest(Prescription prescription) {
        if (prescription.medicationProvided) {
            this.medicationProvided = false;
        } else {
            this.medicationProvided = true;
        }
    }
}


package zw.co.danhiko.medichronicle.models.PrescriptionDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.Medicines.MedicineDetails;
import zw.co.danhiko.medichronicle.models.Pharmacy.Pharmacy;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.Patient;

import java.util.List;
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
   // @ManyToOne
  //  private Patient patient;
  //  @ManyToOne
  //  private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;
//@ManyToMany
  //  private List<MedicineDetails> medicines;
       private boolean medicationProvided;
    // Additional fields and methods...
        public void setMedicationRequest(Prescription prescription) {
        if (prescription.medicationProvided) {
            this.medicationProvided = false;
        } else {
            this.medicationProvided = true;
        }
    }
}


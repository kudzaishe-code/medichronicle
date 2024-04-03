package zw.co.danhiko.medichronicle.models.PrescriptionDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "prescriptions_dp")
public class PrescriptionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prescription;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientDetails patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorDetails doctor;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private PharmacyDetails pharmacy;
    @Column(name = "medication_provided")
    private boolean medicationProvided;
    public void setMedicationRequest(String noMedicationProvided) {
        if ("noMedicationProvided".equalsIgnoreCase(noMedicationProvided)) {
            this.medicationProvided = false;
        } else {
            this.medicationProvided = true;
        }
    }
}

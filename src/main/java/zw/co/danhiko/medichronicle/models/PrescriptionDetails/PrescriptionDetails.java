package zw.co.danhiko.medichronicle.models.PrescriptionDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.Medicines.MedicineDetails;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;

import java.util.List;

//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
//import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
//
//import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
//import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
//
//import java.util.List;
//
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
//@Entity(name = "prescriptions_dp")
//public class PrescriptionDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String prescription;
//    @OneToMany
//    private List<MedicalRecords> medicalRecords;
//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private PatientDetails patient;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_id")
//    private DoctorDetails doctor;
//
//    @ManyToOne
//    @JoinColumn(name = "pharmacy_id")
//    private PharmacyDetails pharmacy;
//    @Column(name = "medication_provided")


@Entity(name = "prescriptions")
public class PrescriptionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other fields...

    @OneToOne
    private MedicalRecords medicalRecord;

    @ManyToOne
    private PatientDetails patient;

    @ManyToOne
    private DoctorDetails doctor;

    @ManyToOne
    private PharmacyDetails pharmacy;

    @ManyToMany
    private List<MedicineDetails> medicines;
       private boolean medicationProvided;
    // Additional fields and methods...
        public void setMedicationRequest(PrescriptionDetails prescriptionDetails) {
        if (prescriptionDetails.medicationProvided) {
            this.medicationProvided = false;
        } else {
            this.medicationProvided = true;
        }
    }
}


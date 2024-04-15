package zw.co.danhiko.medichronicle.repository.Prescription;

import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository {

    PrescriptionDetails save(PrescriptionDetails prescription);

    Optional<PrescriptionDetails> findById(String patientNationalId);



    List<String> findAllMedicationByPatientNationalIdIgnoreCase(String patientNationalId);
    List<PrescriptionDetails> findByPatientNationalIdIgnoreCase(String patientNationalId);
    void deleteById(String patientNationalId);

    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);
}






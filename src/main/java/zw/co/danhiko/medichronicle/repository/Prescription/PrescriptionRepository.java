package zw.co.danhiko.medichronicle.repository.Prescription;

import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;


import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository {
    Optional<PrescriptionDetails> getPrescriptionsByPatientNationalId(String patientNationalId);

    PrescriptionDetails save(PrescriptionDetails prescription);

    Optional<PrescriptionDetails> findById(String patientNationalId);
    Optional<PrescriptionDetails> findById(Long id);

    List<String> findAllMedicationByPatientNationalIdIgnoreCase(String patientNationalId);

    void deleteById(String patientNationalId);

    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);
}






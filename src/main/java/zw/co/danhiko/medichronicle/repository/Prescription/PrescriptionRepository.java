package zw.co.danhiko.medichronicle.repository.Prescription;

import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;


import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository {

    Prescription save(String prescription);

    Optional<Prescription>findById(Long id);



    List<String> findAllMedicationByPatientNationalIdIgnoreCase(String patientNationalId);
    List<Prescription> findByPatientNationalIdIgnoreCase(String patientNationalId);
    void deleteById(String patientNationalId);

    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);

    Optional<Object> findById(String patientNationalId);
}







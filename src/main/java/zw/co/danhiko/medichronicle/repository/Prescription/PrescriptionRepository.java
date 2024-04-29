package zw.co.danhiko.medichronicle.repository.Prescription;

import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;


import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository {

    Prescription save(Prescription prescription);

    Optional<Prescription> findById(String patientNationalId);



    List<String> findAllMedicationByPatientNationalIdIgnoreCase(String patientNationalId);
    List<Prescription> findByPatientNationalIdIgnoreCase(String patientNationalId);
    void deleteById(String patientNationalId);

    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);
}






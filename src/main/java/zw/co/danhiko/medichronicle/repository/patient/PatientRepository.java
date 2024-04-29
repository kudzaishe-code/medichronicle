package zw.co.danhiko.medichronicle.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.patient.Patient;


import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional <Patient> findByPatientNationalIdIgnoreCase(String patientNationalId);
    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);
    List<Patient> findByDoctor(Doctor doctor);
}




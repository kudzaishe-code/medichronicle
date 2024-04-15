package zw.co.danhiko.medichronicle.repository.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails, Long> {
    Optional <PatientDetails> findByPatientNationalIdIgnoreCase(String patientNationalId);

 ;
    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);


    List<PatientDetails> findByDoctor(DoctorDetails doctor);
}




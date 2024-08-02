package zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.patient.Patient;


public interface PatientService {
    Page<Patient> getAllPatients(Pageable pageable);
    ResponseEntity<Patient> createPatient(PatientRegistration request);
    Patient deletePatient(String patientNationalId);
    ResponseEntity<Patient> updatePatientDetailsByPatientNationalIdIgnoreCase(String patientNationalId, PatientUpdateRequest request);
 ResponseEntity<Patient> getPatientDetailsByPatientNationalIdIgnoreCase(String patientNationalId);

}


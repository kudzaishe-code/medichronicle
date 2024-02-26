package zw.co.danhiko.medichronicle.service.medichronicle;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.PatientRegistration;


public interface PatientService {
    Page<PatientDetails> getAllPatients(Pageable pageable);

    ResponseEntity<PatientDetails> getPatientByNationalId(String nationalId);

    ResponseEntity<PatientDetails> createPatient(PatientRegistration request);

    ResponseEntity<PatientDetails> updatePatientByNationalId(String nationalId, PatientUpdateRequest request);

    PatientDetails deletePatient(String nationalId);
}

package zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientRegistration;

import java.util.Date;


public interface PatientService {
    Page<PatientDetails> getAllPatients(Pageable pageable);

    ResponseEntity<PatientDetails> getPatientDetailsByNationalId(String nationalId);

    ResponseEntity<PatientDetails> createPatient(PatientRegistration request);

    ResponseEntity<PatientDetails> updatePatientDetailsByNationalId(String nationalId, PatientUpdateRequest request);

    PatientDetails deletePatient(String nationalId);
    ResponseEntity<PatientDetails> getPatientsByDateRange(Date dayAdmitted, Date dayDischarged);

}

package zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;

import java.util.Date;


public interface PatientService {
    Page<PatientDetails> getAllPatients(Pageable pageable);
    ResponseEntity<PatientDetails> createPatient(PatientRegistration request);
    PatientDetails deletePatient(String patientNationalId);
    ResponseEntity<PatientDetails> updatePatientDetailsByPatientNationalIdIgnoreCase(String patientNationalId, PatientUpdateRequest request);
}


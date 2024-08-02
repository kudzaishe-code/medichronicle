package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;

import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

;import java.util.List;

public interface MedicalRecordService {


 //ResponseEntity<MedicalRecords> getAllPatientMedicalRecordByPatientNationalId(String patientNationalId);
   ResponseEntity<MedicalRecords> deletePatientMedicalRecordsByPatientNationalId(String patientNationalId);
    ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(String patientNationalId, Patient patient);
   ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request);
   List<MedicalRecords> getAllPatientsMedicalRecords();
    ResponseEntity<MedicalRecords> deleteAllMedicalRecords();
    List<MedicalRecords>getAllPatientMedicalRecordByPatientNationalId(String patientNationalId);
}

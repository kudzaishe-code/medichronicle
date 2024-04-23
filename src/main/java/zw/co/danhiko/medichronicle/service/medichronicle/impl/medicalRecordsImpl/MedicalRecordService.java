package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;

import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

;import java.util.List;

public interface MedicalRecordService {

   List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(String patientNationalId);
   List<MedicalRecords>getAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId);
      ResponseEntity<MedicalRecords> deletePatientMedicalRecordsByPatientNationalId(String patientNationalId);

    ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(String patientNationalId, Patient patient);
   List<MedicalRecords> getPatientMedicalRecordsByDoctorNationalId(String patientNationalId);
   ResponseEntity<MedicalRecords>createMedicalRecord(PatientTreatmentRequest request);

    List<MedicalRecords> getAllPatientsMedicalRecords();

    ResponseEntity<MedicalRecords> deleteAllMedicalRecords();
}

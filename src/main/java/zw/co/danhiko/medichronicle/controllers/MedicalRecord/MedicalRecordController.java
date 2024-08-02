package zw.co.danhiko.medichronicle.controllers.MedicalRecord;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl.MedicalRecordService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/medical-records")
@CrossOrigin
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @GetMapping("/get-all-patients-medical-records")
    public List<MedicalRecords> getAllPatientsMedicalRecords() {
        return medicalRecordService.getAllPatientsMedicalRecords();
    }

    @DeleteMapping("/delete/{patientNationalId}")
    public ResponseEntity<MedicalRecords> deletePatient(@PathVariable String patientNationalId) {
        return medicalRecordService.deletePatientMedicalRecordsByPatientNationalId(patientNationalId);
    }

    //    @GetMapping("/get-patient-medical-records-by-patient-national-id/{patientNationalId}")
//    public ResponseEntity<MedicalRecords> getPatientMedicalRecordByPatientNationalId(@PathVariable String patientNationalId){
//        return medicalRecordService.getAllPatientMedicalRecordByPatientNationalId(patientNationalId);
//    }
    @PutMapping("/update/{patientNationalId}")
    public ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(@PathVariable String patientNationalId,
                                                                                         @RequestBody Patient patient) {
        return medicalRecordService.updatePatientMedicalRecordsByPatientNationalId(patientNationalId, patient);
    }

    @DeleteMapping
    public ResponseEntity<MedicalRecords> deleteAllMedicalRecords() {
        return medicalRecordService.deleteAllMedicalRecords();
    }

    @PostMapping("/create-medical-record")
    public ResponseEntity<MedicalRecords> createMedicalRecord(@RequestBody PatientTreatmentRequest patientTreatmentRequest) {
        return medicalRecordService.createMedicalRecord(patientTreatmentRequest);
    }

    @GetMapping("/get-patient-medical-records-by-patient-national-id/{patientNationalId}")
    public List<MedicalRecords> getAllPatientMedicalRecordByPatientNationalId(@PathVariable String patientNationalId) {
        return medicalRecordService.getAllPatientMedicalRecordByPatientNationalId(patientNationalId);
    }
}
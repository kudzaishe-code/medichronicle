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
//    @PostMapping("/create-medical-record/{patientNationalId}/{doctorNationalId}")
//    public ResponseEntity<?> createMedicalRecord(@RequestBody  PatientTreatmentRequest patientTreatmentRequest,String hospitalAddress,String patientNationalId,String doctorNationalId) {
//        // Logic to handle the creation of a medical record
//        medicalRecordService.createPatientMedicalRecord(patientTreatmentRequest,hospitalAddress, doctorNationalId, patientNationalId);
//        return ResponseEntity.ok().build();
//    }
  //  @PostMapping("/create-patient-medical-record")
 //   public ResponseEntity<MedicalRecords> createPatientMedicalRecord(@RequestBody PatientTreatmentRequest patientTreatmentRequest, @RequestParam String doctorNationalId, @RequestParam String patientNationalId) {
 //       return medicalRecordService.createPatientMedicalRecord(patientTreatmentRequest, doctorNationalId, patientNationalId);
//}
    @GetMapping("/get-all-patients-medical-records")
    public List<MedicalRecords> getAllPatientsMedicalRecords() {
        return medicalRecordService.getAllPatientsMedicalRecords();
    }
    @GetMapping("/get-all-patients-medical-records-by-doctor-national-Id/{doctorNationalId}")
    public List<MedicalRecords> getAllPatientsMedicalRecordsByDoctorNationalId(@PathVariable String doctorNationalId) {
        return medicalRecordService.getAllPatientsMedicalRecordsByDoctorNationalId(doctorNationalId);
    }


    @DeleteMapping("/delete/{patientNationalId}")
    public ResponseEntity<MedicalRecords> deletePatient(@PathVariable String patientNationalId) {
        return medicalRecordService.deletePatientMedicalRecordsByPatientNationalId(patientNationalId);
    }

    @GetMapping("/get-patient-medical-records-by-patient-national-id/{patientNationalId}")
    public List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(@PathVariable String patientNationalId){
        return medicalRecordService.getPatientMedicalRecordByPatientNationalId(patientNationalId);
    }

    @PutMapping("/update/{patientNationalId}")
    public ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(@PathVariable String patientNationalId,
                                                                                         @RequestBody Patient patient){
        return medicalRecordService.updatePatientMedicalRecordsByPatientNationalId(patientNationalId, patient);
    }

    @GetMapping("/get-patient-medical-records-by-doctor-national-id/{doctorNationalId}")
    public List<MedicalRecords> getPatientMedicalRecordsByDoctorNationalId(@PathVariable String doctorNationalId){
        return medicalRecordService.getPatientMedicalRecordsByDoctorNationalId(doctorNationalId);
    }
    @DeleteMapping
    public ResponseEntity<MedicalRecords> deleteAllMedicalRecords(){
        return medicalRecordService.deleteAllMedicalRecords();

    }
@PostMapping("/create-medical-record")
public ResponseEntity<MedicalRecords> createMedicalRecord(@RequestBody PatientTreatmentRequest patientTreatmentRequest){

    return medicalRecordService.createMedicalRecord(patientTreatmentRequest);
}
}

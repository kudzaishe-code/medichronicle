package zw.co.danhiko.medichronicle.controllers.patient;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientService;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientRegistration;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patient")
@CrossOrigin
public class PatientController {
    private  final PatientService patientService;

    @GetMapping("/{get-all-patients}")
    public Page<PatientDetails> getAllPatients(@RequestParam (defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return patientService. getAllPatients(pageable);
    }
    @GetMapping("/get-patient-by-nationalId/{nationalId}")
    public ResponseEntity<PatientDetails> getPatientDetailsByNationalId(@PathVariable String nationalId) {
        return patientService.getPatientDetailsByNationalId(nationalId);
    }

    @PostMapping("/{create-patient}")
    public ResponseEntity<PatientDetails> createPatient(@RequestBody PatientRegistration request) {
        return patientService.createPatient(request);
    }

    @PutMapping("/update/{nationalId}")
    public ResponseEntity<PatientDetails> updatePatientDetailsByNationalId(@PathVariable String nationalId ,
                                                                    @RequestBody PatientUpdateRequest patientUpdateRequest) {
        return patientService.updatePatientDetailsByNationalId(nationalId, patientUpdateRequest);

    }
    @DeleteMapping("/delete/{nationalId}")
    public PatientDetails deletePatient(@PathVariable String nationalId) {
        return patientService.deletePatient(nationalId);
    }
@GetMapping("/get-patient-by-date")
public ResponseEntity<PatientDetails> getPatientsByDateRange(@RequestParam Date dayAdmitted, @RequestParam Date dayDischarged) {
    return patientService.getPatientsByDateRange(dayAdmitted, dayDischarged);

}

}
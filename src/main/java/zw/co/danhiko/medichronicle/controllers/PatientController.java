package zw.co.danhiko.medichronicle.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.PatientService;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.PatientRegistration;


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
    @GetMapping("/{get-patient-by-id}")
    public ResponseEntity<PatientDetails> getPatientByNationalId(@PathVariable String nationalId) {
        return patientService.getPatientByNationalId(nationalId);
    }

    @PostMapping("/{create-patient}")
    public ResponseEntity<PatientDetails> createPatient(@RequestBody PatientRegistration request) {
        return patientService.createPatient(request);
    }

    @PutMapping("/{update-patient-by-id}")
    public ResponseEntity<PatientDetails> updatePatientbyNationalId(@PathVariable String nationalId , @RequestBody PatientUpdateRequest patientUpdateRequest) {
        return patientService.updatePatientByNationalId(nationalId, patientUpdateRequest);

    }
    @DeleteMapping("/{delete-patient-by-id}")
    public PatientDetails deletePatient(@PathVariable String nationalId) {
        return patientService.deletePatient(nationalId);
    }


}
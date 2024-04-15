package zw.co.danhiko.medichronicle.controllers.patient;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientService;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientRegistration;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/patient")
@CrossOrigin
public class PatientController {
    private  final PatientService patientService;

    @GetMapping("/get-all-patients")
    public Page<PatientDetails> getAllPatients(@RequestParam (defaultValue = "0") Integer pageNumber,
                                               @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return patientService. getAllPatients(pageable);
    }


    @PostMapping("/createPatient")
    public ResponseEntity<PatientDetails> createPatient(@RequestBody PatientRegistration request) {
        return patientService.createPatient(request);
    }


    @DeleteMapping("/delete/{patientNationalId}")
    public PatientDetails deletePatient(@PathVariable String patientNationalId) {
        return patientService.deletePatient(patientNationalId);
    }


}
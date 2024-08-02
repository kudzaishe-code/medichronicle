package zw.co.danhiko.medichronicle.controllers.Prescription;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl.PrescriptionService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

  @PostMapping("/create-prescription-by-medical-record-id")
    public List<Prescription> createPrescription(@RequestParam Long id) {
        List<String> createdPrescriptions = prescriptionService.createPrescription(id);
        return prescriptionService.getPrescriptionsForPatient(id);
    }


    @GetMapping("get-prescription-by-patient-national-id/{patientNationalId}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable String patientNationalId) {
        Prescription prescription = prescriptionService.getPrescriptionById(patientNationalId);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @DeleteMapping("delete-prescription-by-patient-national-id/{patientNationalId}")
    public ResponseEntity<Prescription> deletePrescription(@PathVariable String patientNationalId) {
        prescriptionService.deletePrescriptionByPatientNationalId(patientNationalId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update-prescription-by-patient-national-id/{id}")
    public ResponseEntity<Prescription> updatePrescriptionByPatientNationalId(@PathVariable String patientNationalId, @RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.updatePrescriptionByPatientNationalId(patientNationalId, prescriptionDTO));
    }
    @GetMapping("/{prescriptionId}/medication-provided")
    public boolean isMedicationProvided(@PathVariable Long prescriptionId) {
        return prescriptionService.isMedicationProvided(prescriptionId);
    }






















// getPrescriptionsForPatient
    @GetMapping("/get-prescriptions-for-patient/{patientNationalId}")
    public List<Prescription> getPrescriptionsForPatient(@PathVariable Long id) {
        return prescriptionService.getPrescriptionsForPatient(id);
    }
    @GetMapping("/is-medication-provided/{patientNationalId}")
    public boolean isMedicationProvided(@PathVariable String patientNationalId) {

        return prescriptionService.isMedicationProvided(patientNationalId);
    }

}

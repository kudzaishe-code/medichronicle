package zw.co.danhiko.medichronicle.controllers.Prescription;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl.PrescriptionService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;



    @PostMapping("/create-prescription-by-patient-national-id-and-doctor-national-id/create")
    public ResponseEntity<List<PrescriptionDetails>> createPrescription(@RequestBody PrescriptionDetails prescription, @RequestParam String patientNationalId, @RequestParam String doctorNationalId) {
        List<PrescriptionDetails> createdPrescriptions = prescriptionService.createPrescription(prescription, patientNationalId, doctorNationalId);
        return new ResponseEntity<>(createdPrescriptions, HttpStatus.CREATED);
    }


    @GetMapping("get-prescription-by-patient-national-id/{patientNationalId}")
    public ResponseEntity<PrescriptionDetails> getPrescriptionById(@PathVariable String patientNationalId) {
        PrescriptionDetails prescription = prescriptionService.getPrescriptionById(patientNationalId);
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @DeleteMapping("delete-prescription-by-patient-national-id/{patientNationalId}")
    public ResponseEntity<Void> deletePrescription(@PathVariable String patientNationalId) {
        prescriptionService.deletePrescriptionByPatientNationalId(patientNationalId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("update-prescription-by-patient-national-id/{id}")
    public ResponseEntity<PrescriptionDetails> updatePrescriptionByPatientNationalId(@PathVariable String patientNationalId, @RequestBody PrescriptionDTO prescriptionDTO) {
        return ResponseEntity.ok(prescriptionService.updatePrescriptionByPatientNationalId(patientNationalId, prescriptionDTO));
    }
    @GetMapping("/{prescriptionId}/medication-provided")
    public boolean isMedicationProvided(@PathVariable Long prescriptionId) {
        return prescriptionService.isMedicationProvided(prescriptionId);
    }
}

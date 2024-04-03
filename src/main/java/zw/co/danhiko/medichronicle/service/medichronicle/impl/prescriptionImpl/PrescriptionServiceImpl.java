package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.Pharmacy.PharmacyRepository;
import zw.co.danhiko.medichronicle.repository.Prescription.PrescriptionRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
    private PrescriptionRepository prescriptionRepository;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private PharmacyRepository pharmacyRepository;
    private MedicalRecordsRepository medicalRecordRepository;

    @Override
    public List<PrescriptionDetails> createPrescription(PrescriptionDetails prescription, String patientNationalId, String doctorNationalId) {
        // Check if the patient exists based on the provided national ID
        PatientDetails patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Patient not found with national ID: " + patientNationalId));
        // Check if the doctor exists based on the provided national ID
        DoctorDetails doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with national ID: " + doctorNationalId));
        //check if the medical record exists based on the provided national ID
        MedicalRecords medicalRecord = medicalRecordRepository.findByPatientNationalIdIgnoreCase(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Medical record not found with national ID: " + patientNationalId));
        // create a new prescription based on patient medical record and highlight the medication provided and not provided that will be searched in the pharmacy
        PrescriptionDetails newPrescription = new PrescriptionDetails();
        newPrescription.setPrescription(prescription.getPrescription());
        // check if the medication is provided in the prescription repository or  not and if not then pass it to the  pharmacy
        List<String> medicationListInRepository = prescriptionRepository.findAllMedicationByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicationListInRepository.isEmpty()) {
            newPrescription.setMedicationRequest("No medication provided");
        } else {
            newPrescription.setMedicationRequest(medicationListInRepository.toString());
            // pass the medication to the pharmacy that need to be searched
            PharmacyDetails pharmacyDetails = new PharmacyDetails();
            pharmacyDetails.setMedicationRequest(medicationListInRepository.toString());
            newPrescription.setPharmacy(pharmacyRepository.save(pharmacyDetails));


        }
     // return the new prescription list
        return List.of(prescriptionRepository.save(newPrescription));
        }

        @Override
    public PrescriptionDetails getPrescriptionById(String patientNationalId) {
        return prescriptionRepository.findById(patientNationalId).orElseThrow(() -> new RuntimeException("Prescription not found with id: " + patientNationalId));
    }

    @Override
    public void deletePrescriptionByPatientNationalId(String patientNationalId) {
// check if the prescription exists based on the provided national ID
        if (!prescriptionRepository.existsByPatientNationalIdIgnoreCase(patientNationalId)) {
            throw new RuntimeException("Prescription not found with id: " + patientNationalId);

        }
        prescriptionRepository.deleteById(patientNationalId);

    }

    @Override
    public PrescriptionDetails updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO) {
        // Retrieve the existing prescription by ID
        PrescriptionDetails existingPrescription = prescriptionRepository.findById(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + patientNationalId));

        // Update fields of the existing prescription
        existingPrescription.setPrescription(prescriptionDTO.getPrescription());

        // Retrieve and validate patient, doctor, and pharmacy
        PatientDetails patient = patientRepository.findByPatientNationalIdIgnoreCase(String.valueOf(prescriptionDTO))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        DoctorDetails doctor = (DoctorDetails) doctorRepository.findByDoctorNationalIdIgnoreCase(String.valueOf(prescriptionDTO))
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        PharmacyDetails pharmacy = (PharmacyDetails) pharmacyRepository.findById(Long.valueOf(String.valueOf(prescriptionDTO)))
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        // Update patient, doctor, and pharmacy associations
        existingPrescription.setPatient(patient);
        existingPrescription.setDoctor(doctor);
        existingPrescription.setPharmacy(pharmacy);

        // Save the updated prescription
        return prescriptionRepository.save(existingPrescription);
    }

    @Override
    public boolean isMedicationProvided(Long prescriptionId) {
        PrescriptionDetails prescriptionDetails = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Prescription not found"));
        return prescriptionDetails.isMedicationProvided();
    }
}

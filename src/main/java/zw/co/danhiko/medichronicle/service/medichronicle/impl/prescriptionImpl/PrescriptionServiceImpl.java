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

import java.time.LocalDate;
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
    public List<PrescriptionDetails> createPrescription(String patientNationalId, String doctorNationalId, LocalDate medicalRecordCreationDate) {
        // Check if the patient exists
        PatientDetails patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Patient not found with national ID: " + patientNationalId));
        // Check if the doctor exists
        DoctorDetails doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with national ID: " + doctorNationalId));
        // Check if the medical record exists
        MedicalRecords medicalRecord = medicalRecordRepository.findByPatientNationalIdIgnoreCaseAndDayAdmitted(patientNationalId, medicalRecordCreationDate)
                .orElseThrow(() -> new RuntimeException("Medical record not found with national ID: " + patientNationalId + " and creation date: " + medicalRecordCreationDate));

        // Assuming the MedicalRecords entity has a method to directly get the PrescriptionDetails or create a new one if it does not exist
        PrescriptionDetails newPrescription = medicalRecord.getPrescription();
        if (newPrescription == null) {
            newPrescription = new PrescriptionDetails();
            newPrescription.setPatient(patient);
            newPrescription.setDoctor(doctor);
            // Assuming the PrescriptionDetails entity has methods to set properties such as medication requests and whether medication was provided
            // Set other necessary prescription details here
            newPrescription.setMedicationProvided(false); // Initially, no medication is provided
            medicalRecord.setPrescription(newPrescription); // Link the new prescription to the medical record
        }

        // Save the new prescription. If it's newly created or updated, it needs to be persisted.
        prescriptionRepository.save(newPrescription);

        // Return the created or updated prescription in a list as per your method signature
        return List.of(newPrescription);
    }

    @Override
    public PrescriptionDetails getPrescriptionById(String patientNationalId) {
        return null;
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
        return null;
    }

    @Override
    public boolean isMedicationProvided(Long prescriptionId) {
        return false;
    }

    @Override
    public PrescriptionDetails updatePrescriptionById(String patientNationalId, PrescriptionDTO prescriptionDTO) {
        // Retrieve the existing prescription by ID
        PrescriptionDetails existingPrescription = prescriptionRepository.findById(patientNationalId)
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " +patientNationalId));

        // Update fields of the existing prescription
      //  existingPrescription.setPrescription(prescriptionDTO.getPrescription());

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
    public boolean isMedicationProvided(String patientNationalId) {
        return false;
    }

    @Override
    public List<PrescriptionDetails> getPrescriptionsForPatient(String patientNationalId) {
        return null;
    }


}

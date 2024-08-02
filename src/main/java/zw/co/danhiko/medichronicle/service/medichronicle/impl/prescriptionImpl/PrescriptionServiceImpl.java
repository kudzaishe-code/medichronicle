package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.co.danhiko.medichronicle.dto.Pills.PillAvailabilityDTO;
import zw.co.danhiko.medichronicle.dto.Pills.PillsDetailsDTO;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.Pharmacy.Pharmacy;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.Patient;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.Pharmacy.PharmacyRepository;
import zw.co.danhiko.medichronicle.repository.Prescription.PrescriptionRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<String> createPrescription(Long id) {
        // Check if the medical record exists based on the provided ID
        Optional<MedicalRecords> medicalRecord = medicalRecordRepository.findById(id);
        if (medicalRecord.isEmpty()) {
            throw new RuntimeException("Medical record not found with id: " + id);
        }

        // Create a new prescription
        Prescription prescription = Prescription.builder()
                .prescription(medicalRecord.get().getPrescription())
                .build();
        prescriptionRepository.save(String.valueOf(prescription));
        return List.of(String.valueOf(prescription));
    }

    public List<PillsDetailsDTO> parsePrescription(String prescription) {
        List<PillsDetailsDTO> pills = new ArrayList<>();
        String[] pillDetails = prescription.split(",");

        for (String detail : pillDetails) {
            String[] parts = detail.trim().split(" ");
            if (parts.length == 3) {
                String name = parts[0];
                String dosage = parts[1];
                String frequency = parts[2];

                PillsDetailsDTO pill = new PillsDetailsDTO();
                pill.setName(name);
                pill.setDosage(dosage);
                pill.setFrequency(frequency);
                pills.add(pill);
            }
        }

        return pills;
    }

    @Override
    public Prescription getPrescriptionById(String patientNationalId) {
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
    public Prescription updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO) {
        return null;
    }


    @Override
    public boolean isMedicationProvided(Long prescriptionId) {
        return false;
    }

    @Override
    public Prescription updatePrescriptionById(String patientNationalId, PrescriptionDTO prescriptionDTO, Long prescriptionId) {
        // Retrieve the existing prescription by ID
        Prescription existingPrescription = (Prescription) prescriptionRepository.findById(String.valueOf(prescriptionId))
                .orElseThrow(() -> new RuntimeException("Prescription not found with id: " + prescriptionId));

        // Update fields of the existing prescription
      //  existingPrescription.setPrescription(prescriptionDTO.getPrescription());

        // Retrieve and validate patient, doctor, and pharmacy
        Patient patient = patientRepository.findByPatientNationalIdIgnoreCase(String.valueOf(prescriptionDTO))
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = (Doctor) doctorRepository.findByDoctorNationalIdIgnoreCase(String.valueOf(prescriptionDTO))
                .orElseThrow(() -> new RuntimeException("DoctorAddress not found"));
        Pharmacy pharmacy = (Pharmacy) pharmacyRepository.findById(Long.valueOf(String.valueOf(prescriptionDTO)))
                .orElseThrow(() -> new RuntimeException("Pharmacy not found"));

        // Update patient, doctor, and pharmacy associations
       // existingPrescription.setPatient(patient);
       // existingPrescription.setDoctor(doctor);
        existingPrescription.setPharmacy(pharmacy);

        // Save the updated prescription
        return prescriptionRepository.save(String.valueOf(existingPrescription));
    }

    @Override
    public boolean isMedicationProvided(String patientNationalId) {
        return false;
    }

    @Override
    public List<Prescription> getPrescriptionsForPatient(Long id) {
        return List.of();
    }


    public List<PillAvailabilityDTO> checkPillsAvailability(String prescription, List<String> availablePills) {
        List<PillsDetailsDTO> prescribedPills = parsePrescription(prescription);
        List<PillAvailabilityDTO> result = new ArrayList<>();

        for (PillsDetailsDTO pill : prescribedPills) {
            PillAvailabilityDTO pillAvailability = new PillAvailabilityDTO();
            pillAvailability.setPillDetails(pill);
            pillAvailability.setProvided(availablePills.contains(pill.getName()));
            result.add(pillAvailability);
        }

        return result;
    }



}

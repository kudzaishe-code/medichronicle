package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.DoctorNotFoundException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class MedicalRecordsServiceImpl implements MedicalRecordService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Override
    public List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(String patientNationalId) {
        Optional<PatientDetails> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (patient == null)
            throw new FileDoesNotExistException("patient does not exist");

       List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
        return ResponseEntity.ok(medicalRecords).getBody();
    }
    @Override
    public List<MedicalRecords> getAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId) {
        // Check if the doctor exists
        Optional<DoctorDetails> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
        if (doctorOptional.isEmpty()) {
            throw new DoctorNotFoundException("Doctor with national ID: " + doctorNationalId + " not found");
        }

        return medicalRecordsRepository.findAllByDoctorNationalIdIgnoreCase(doctorNationalId);
    }

@Override
public ResponseEntity<MedicalRecords> addPatientMedicalRecords(PatientTreatmentRequest patientTreatmentRequest,
                                                               String doctorNationalId, String patientNationalId) {
    // Check if the doctor exists
    Optional<DoctorDetails> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
    if (doctorOptional.isEmpty()) {
        // Doctor does not exist, return an appropriate response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Check if the patient exists
    Optional<PatientDetails> patientOptional = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
    if (patientOptional.isEmpty()) {
        // Patient does not exist, return an appropriate response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Doctor and patient both exist, proceed to add medical records
//
//    Optional<MedicalRecords> existingMedicalRecordOptional = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//
//    MedicalRecords medicalRecord;
//
//    if (existingMedicalRecordOptional.isPresent()) {
//        // Append the new treatment details to existing medical record
//        medicalRecord = existingMedicalRecordOptional.get();
//        appendTreatmentDetails(medicalRecord, patientTreatmentRequest);
//    } else {
//        // Create a new medical record
//        medicalRecord = createMedicalRecord(patientTreatmentRequest, patientNationalId);
//    }
    MedicalRecords medicalRecords = MedicalRecords.builder()
            .patientName(patientTreatmentRequest.getPatientName())
            .patientNationalId(patientNationalId)
            .doctorNationalId(doctorNationalId)
            .chronicDisease(patientTreatmentRequest.getChronicDisease())
            .dayAdmitted(patientTreatmentRequest.getDayAdmitted())
            .prescription(patientTreatmentRequest.getPrescription())
            .referral(patientTreatmentRequest.getReferral())
            .temperature(patientTreatmentRequest.getTemperature())
            .bp(patientTreatmentRequest.getBp())
            .weight(patientTreatmentRequest.getWeight())
            .diagnosis(patientTreatmentRequest.getDiagnosis())
            .pulse(patientTreatmentRequest.getPulse())
            .build();

    MedicalRecords saved_medicalRecord = medicalRecordsRepository.save(medicalRecords);
    return ResponseEntity.ok(saved_medicalRecord);
}

    private MedicalRecords createMedicalRecord(PatientTreatmentRequest patientTreatmentRequest, String patientNationalId) {
        return MedicalRecords.builder()
                .patientName(patientTreatmentRequest.getPatientName())
                .patientNationalId(patientNationalId)
                .chronicDisease(patientTreatmentRequest.getChronicDisease())
                .dayAdmitted(patientTreatmentRequest.getDayAdmitted())
                .prescription(patientTreatmentRequest.getPrescription())
                .referral(patientTreatmentRequest.getReferral())
                .temperature(patientTreatmentRequest.getTemperature())
                .bp(patientTreatmentRequest.getBp())
                .weight(patientTreatmentRequest.getWeight())
                .build();
    }

    private void appendTreatmentDetails(MedicalRecords medicalRecord, PatientTreatmentRequest patientTreatmentRequest) {
        // Append new treatment details to existing medical record
        // You can define the logic to append the treatment details here
        // For simplicity, let's assume you just replace the existing treatment details
        medicalRecord.setChronicDisease(patientTreatmentRequest.getChronicDisease());
        medicalRecord.setDayAdmitted(patientTreatmentRequest.getDayAdmitted());
        medicalRecord.setPrescription(patientTreatmentRequest.getPrescription());
        medicalRecord.setReferral(patientTreatmentRequest.getReferral());
        medicalRecord.setTemperature(patientTreatmentRequest.getTemperature());
        medicalRecord.setBp(patientTreatmentRequest.getBp());
        medicalRecord.setWeight(patientTreatmentRequest.getWeight());

    }
    public List<PatientDetails> findPatientsTreatedByDoctor(String doctorNationalId) {
        // Search for patients treated by the doctor with the given national ID
        Optional<DoctorDetails> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
        if (doctorOptional.isPresent()) {
            DoctorDetails doctorDetails = doctorOptional.get();
            // Retrieve the list of patients treated by this doctor
            return patientRepository.findByDoctor(doctorDetails);
        } else {
            // If doctor not found, return an empty list
            return Collections.emptyList();
        }
    }

    @Override
    public ResponseEntity<MedicalRecords> deletePatientMedicalRecordsByPatientNationalId(String patientNationalId) {
        Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("patient does not exist");
        } else {
            medicalRecordsRepository.delete(medicalRecords.get());
            return ResponseEntity.ok(medicalRecords.get());
        }

    }
    @Override
    public ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(String patientNationalId, Patient patient) {

        Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("patient does not exist");
        } else {
            MedicalRecords medicalRecords1 = medicalRecords.get();
            medicalRecords1.setPatientName(patient.getPatientName());
            medicalRecords1.setChronicDisease(patient.getChronicDisease());
            medicalRecords1.setDayAdmitted(patient.getDayAdmitted());
            medicalRecords1.setPrescription(patient.getPrescription());
            medicalRecords1.setReferral(patient.getReferral());
            medicalRecords1.setTemperature(patient.getTemperature());
            medicalRecords1.setBp(patient.getBp());
            medicalRecords1.setWeight(patient.getWeight());
            medicalRecordsRepository.save(medicalRecords1);
            return ResponseEntity.ok(medicalRecords1);
        }
    }
    @Override
    public List<MedicalRecords> getPatientMedicalRecordsByDoctorNationalId(String doctorNationalId) {

        if (medicalRecordsRepository.findAllPatientsMedicalRecordsByDoctorNationalId(doctorNationalId).isEmpty()) {
            throw new FileDoesNotExistException("No medical records found for doctor with national ID: " + doctorNationalId);
        }

        return medicalRecordsRepository.findAllPatientsMedicalRecordsByDoctorNationalId(doctorNationalId);
    }

    @Override
    public List<MedicalRecords> getAllPatientsMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }
}


package zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.patient.Patient;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;


    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<Patient> createPatient(PatientRegistration request) {
        if (patientRepository.existsByPatientNationalIdIgnoreCase(request.getPatientNationalId()))
            throw new RecordAlreadyExistException("patient  with id already exist");
        Patient patient = Patient.builder()
                .patientName(request.getPatientName())
                .patientNationalId(request.getPatientNationalId())
                .chronicDisease(request.getChronicDisease())
                .build();
        patient = patientRepository.save(patient);

        return ResponseEntity.ok(patient);
    }

    @Override
    public Patient deletePatient(String patientNationalId) {
        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (patient.isEmpty())
            throw new FileDoesNotExistException("patient does not exist");

        patientRepository.delete(patient.get());
        return patient.get();
    }

    @Override
    public ResponseEntity<Patient> updatePatientDetailsByPatientNationalIdIgnoreCase(String patientNationalId, PatientUpdateRequest request) {

        //logic to  update patient details
        if (!patientRepository.existsByPatientNationalIdIgnoreCase(patientNationalId))
            throw new FileDoesNotExistException("patient does not exist");
        Patient patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId).get();
        patient.setChronicDisease(request.getChronicDisease());
        patient = patientRepository.save(patient);
        return ResponseEntity.ok(patient);
    }

    @Override
    public ResponseEntity<Patient> getPatientDetailsByPatientNationalIdIgnoreCase(String patientNationalId) {

        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (patient.isEmpty())
            throw new FileDoesNotExistException("patient does not exist");
        return ResponseEntity.ok(patient.get());
    }
}
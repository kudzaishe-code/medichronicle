package zw.co.danhiko.medichronicle.service.medichronicle.impl;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.repository.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.PatientService;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;

    @Override
    public Page<PatientDetails> getAllPatients(Pageable pageable) {


        return patientRepository.findAll(pageable);
    }

    @Override
    public ResponseEntity<PatientDetails> getPatientByNationalId(String nationalId) {
        Optional<PatientDetails> patientDetails = patientRepository.findByNationalId(nationalId);
        if (patientDetails.isEmpty())
            throw new FileDoesNotExistException("patient does not exist");

        return ResponseEntity.ok(patientDetails.get());
    }

    @Override
    public ResponseEntity<PatientDetails> createPatient(PatientRegistration request) {
        if (patientRepository.existsByNationalId(request.getNationalId()))
            throw new RecordAlreadyExistException("patient  with id already exist");
            PatientDetails patientDetails = PatientDetails.builder()
                    .patientName(request.getPatientName())
                    .nationalId(request.getNationalId())
                    .chronicDisease(request.getChronicdisease())
                    .build();
            patientDetails = patientRepository.save(patientDetails);

            return ResponseEntity.ok(patientDetails);
        }



    @Override
    public ResponseEntity<PatientDetails> updatePatientByNationalId(String nationalId, PatientUpdateRequest request) {
        if (patientRepository.existsByNationalId(nationalId))
            throw new RecordAlreadyExistException("patient with id already exist");
        PatientDetails patientUpdateRequest = patientRepository.findByNationalId(nationalId)
                .orElseThrow(()->  new FileDoesNotExistException("Patient does not exist"));

        patientUpdateRequest.setHospitalName(request.getHospitalName());
        patientUpdateRequest.setChronicDisease(request.getChronicDisease());
        patientUpdateRequest.setPrescription(request.getPrescription());
        patientUpdateRequest.setDayAdmitted(request.getDayAdmitted());
        patientUpdateRequest.setDayDischarged(request.getDayDischarged());
        patientUpdateRequest.setReferral(request.getReferral());
        patientUpdateRequest.setTemperature(request.getTemperature());
        patientUpdateRequest.setBp(request.getBp());
        patientUpdateRequest.setDoctorName(request.getDoctorName());

        patientUpdateRequest = patientRepository.save(patientUpdateRequest);
        return ResponseEntity.ok(patientUpdateRequest);
    }


    @Override
        public PatientDetails deletePatient (String nationalId ){
            Optional<PatientDetails> patient = patientRepository.findByNationalId(nationalId);
            if(patient.isEmpty())
                throw new FileDoesNotExistException("patient does not exist");

            patientRepository.delete(patient.get());
            return  patient.get();

        }

    }

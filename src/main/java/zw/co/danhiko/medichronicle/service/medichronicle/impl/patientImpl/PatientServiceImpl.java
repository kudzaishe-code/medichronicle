package zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordNotFoundException;

import java.util.Date;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class PatientServiceImpl implements PatientService {

  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;


    @Override
    public Page<PatientDetails> getAllPatients(Pageable pageable) {


        return patientRepository.findAll(pageable);
    }



    @Override
    public ResponseEntity<PatientDetails> createPatient(PatientRegistration request) {
        if (patientRepository.existsByPatientNationalIdIgnoreCase(request.getPatientNationalId()))
            throw new RecordAlreadyExistException("patient  with id already exist");
            PatientDetails patientDetails = PatientDetails.builder()
                    .patientName(request.getPatientName())
                    .patientNationalId(request.getPatientNationalId())
                    .chronicDisease(request.getChronicDisease())
                    .build();
            patientDetails = patientRepository.save(patientDetails);

            return ResponseEntity.ok(patientDetails);
        }
    @Override
        public PatientDetails deletePatient (String patientNationalId ){
            Optional<PatientDetails> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
            if(patient.isEmpty())
                throw new FileDoesNotExistException("patient does not exist");

            patientRepository.delete(patient.get());
            return  patient.get();

        }





    }



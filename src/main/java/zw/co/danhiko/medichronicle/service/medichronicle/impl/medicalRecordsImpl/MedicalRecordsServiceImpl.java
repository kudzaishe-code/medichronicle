package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;


import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.Patient;
import zw.co.danhiko.medichronicle.repository.HospitalDetails.HospitalRepository;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.Pills.PillRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
   public class MedicalRecordsServiceImpl implements MedicalRecordService {
    private final MedicalRecordsRepository medicalRecordsRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;
    private final PillRepository pillRepository;
    private static final Logger logger = LoggerFactory.getLogger(MedicalRecordsServiceImpl.class);


//    @Override
//    public List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(String patientNationalId) {
//        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//        if (medicalRecords.isEmpty())
//            throw new FileDoesNotExistException("No medical records found for patient with national ID: " + patientNationalId);
//            return medicalRecords;
//
//        }
//public ResponseEntity<MedicalRecords> getAllPatientMedicalRecordByPatientNationalId(String patientNationalId) {
//    Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//    if (medicalRecords.isEmpty())
//        throw new FileDoesNotExistException("No medical records found for patient with national ID: " + patientNationalId);
//
//    return ResponseEntity.ok(medicalRecords.get());
//}


//    public List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(String patientNationalId) {
////get patient medical records
//        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//        if (patient.isEmpty())
//            throw new FileDoesNotExistException("patient does not exist");
//        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//        if (medicalRecords.isEmpty()) {
//            throw new FileDoesNotExistException("patient medical record does not exist");
//        }
//        return medicalRecords;
//    }
//        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//        if (patient.isEmpty())
//            throw new FileDoesNotExistException("patient does not exist");
//        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//        if (medicalRecords.isEmpty()) {
//            throw new FileDoesNotExistException("patient medical record does not exist");
//        }
//        return ResponseEntity.ok(medicalRecords).getBody();
//    }
//        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//        if (patient.isEmpty())
//            throw new FileDoesNotExistException("patient does not exist");
//       List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//       if (medicalRecords.isEmpty()) {
//           throw new FileDoesNotExistException("patient medical record does not exist");
//       }
//        return ResponseEntity.ok(medicalRecords).getBody();
//    }

    // logic to get all patients medical records using doctor national id


//    public List<MedicalRecords> getAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId) {
//        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
//        if (doctorOptional.isEmpty()) {
//            throw new DoctorNotFoundException("DoctorAddress with national ID: " + doctorNationalId + " not found");
//        }
//        return medicalRecordsRepository.findAllByDoctorNationalIdIgnoreCase(doctorNationalId);
//    }


    @Override
    public ResponseEntity<MedicalRecords> deletePatientMedicalRecordsByPatientNationalId(String patientNationalId) {
        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("patient does not exist");
        } else {
            medicalRecordsRepository.delete(medicalRecords.get(0));
            return ResponseEntity.ok(medicalRecords.get(0));
        }
    }

    @Override
    public ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(String patientNationalId, zw.co.danhiko.medichronicle.dto.patient.Patient patient) {

        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("patient does not exist");
        } else {
            MedicalRecords medicalRecords1 = medicalRecords.get(0);
            //  medicalRecords1.setPatientName(patient.getPatientName());
            // medicalRecords1.setChronicDisease(patient.getChronicDisease());
            medicalRecords1.setDayAdmitted(patient.getDayAdmitted());
            medicalRecords1.setPrescription(String.valueOf(patient.getPrescription()));
            medicalRecords1.setReferral(patient.getReferral());
            medicalRecords1.setTemperature(patient.getTemperature());
            medicalRecords1.setBp(patient.getBp());
            medicalRecords1.setWeight(patient.getWeight());
            medicalRecordsRepository.save(medicalRecords1);
            return ResponseEntity.ok(medicalRecords1);
        }
    }


    @Override
    @Transactional
    public ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request) {


        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(request.getDoctorNationalId());
        Optional<Patient> patientOptional = patientRepository.findByPatientNationalIdIgnoreCase(request.getPatientNationalId());
        Optional<Hospital> hospitalOptional = hospitalRepository.findByHospitalAddressIgnoreCase(request.getHospitalAddress());

        if (doctorOptional.isEmpty() || patientOptional.isEmpty() || hospitalOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Create MedicalRecords object with associated entities
        MedicalRecords medicalRecords = MedicalRecords.builder()
                .doctor(doctorOptional.get())
                .patient(patientOptional.get())
                .hospital(hospitalOptional.get())
                .dayAdmitted(request.getDayAdmitted())
                .prescription(request.getPrescription())
                .referral(request.getReferral())
                .temperature(request.getTemperature())
                .bp(request.getBp())
                .weight(request.getWeight())
                .diagnosis(request.getDiagnosis())
                .build();

        // Save the MedicalRecords object to the database
        medicalRecords = medicalRecordsRepository.save(medicalRecords);

        // Parse the prescription and create a list of pills
        List<PillsDetails> pills = parsePrescription(request.getPrescription(), medicalRecords);

        // Save the list of pills to the database
        pills = pillRepository.saveAll(pills);

        // Return the saved MedicalRecords object and pills in the response
        MedicalRecordResponse response = new MedicalRecordResponse(medicalRecords, pills);

        // Return the saved MedicalRecords object in the response
        return ResponseEntity.ok(medicalRecords);
    }

    // parsePrescription method
    private List<PillsDetails> parsePrescription(String prescription, MedicalRecords medicalRecords) {
        List<PillsDetails> pills = new ArrayList<>();
        String[] pillDetails = prescription.split(","); // Splitting the prescription into individual pill details

        for (String detail : pillDetails) {
            String[] parts = detail.trim().split(" "); // Splitting each pill detail into its components
            if (parts.length == 3) {
                String name = parts[0];
                String dosage = parts[1];
                String frequency = parts[2];

                // Creating a Pill object with the extracted components
                PillsDetails pill = PillsDetails.builder()
                        .name(name)
                        .dosage(dosage)
                        .frequency(frequency)
                        //.medicalRecords(medicalRecords) // Associating the Pill with the MedicalRecords
                        .build();
                pills.add(pill);
            }
        }

        return pills; // Returning the list of created Pill objects
    }


    @Override
    public List<MedicalRecords> getAllPatientsMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }


    @Override
    public ResponseEntity<MedicalRecords> deleteAllMedicalRecords() {

        medicalRecordsRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

//    @Override
//    public ResponseEntity<MedicalRecords> getAllPatientMedicalRecordByPatientNationalId(String patientNationalId) {
//
//        Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
//        if (medicalRecords.isEmpty())
//            throw new FileDoesNotExistException("patient medical record does not exist");
//        return ResponseEntity.ok(medicalRecords.get());
//
//
//    }

    @Override
    public List<MedicalRecords> getAllPatientMedicalRecordByPatientNationalId(String patientNationalId) {
        logger.info("Fetching medical records for patientNationalId: {}", patientNationalId);
        List<MedicalRecords> records = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (records.isEmpty()) {
            logger.error("No medical records found for patientNationalId: {}", patientNationalId);
            throw new FileDoesNotExistException("Patient medical record does not exist");
        }
        logger.info("Found {} records for patientNationalId: {}", records.size(), patientNationalId);
        return records;
    }
}
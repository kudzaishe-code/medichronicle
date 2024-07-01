package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;


import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.Patient;
import zw.co.danhiko.medichronicle.repository.HospitalDetails.HospitalRepository;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;

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

    @Override
    public List<MedicalRecords> getPatientMedicalRecordByPatientNationalId(String patientNationalId) {

        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (patient.isEmpty())
            throw new FileDoesNotExistException("patient does not exist");
       List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByPatientNationalIdIgnoreCase(patientNationalId);
       if (medicalRecords.isEmpty()) {
           throw new FileDoesNotExistException("patient medical record does not exist");
       }
        return ResponseEntity.ok(medicalRecords).getBody();
    }

    // logic to get all patients medical records using doctor national id
    @Override
    public List<MedicalRecords> getPatientMedicalRecordByDoctorNationalId(String doctorNationalId) {

        Optional<Doctor> doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
        if (doctor.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");
        List<MedicalRecords> medicalRecords = medicalRecordsRepository.findAllByDoctorNationalIdIgnoreCase(doctorNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("doctor medical record does not exist");
        }
        return ResponseEntity.ok(medicalRecords).getBody();
    }

    @Override
    public List<MedicalRecords> getAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId) {
        return null;
    }
//    @Override
//    public List<MedicalRecords> getAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId) {
//        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
//        if (doctorOptional.isEmpty()) {
//            throw new DoctorNotFoundException("DoctorAddress with national ID: " + doctorNationalId + " not found");
//        }
//        return medicalRecordsRepository.findAllByDoctorNationalIdIgnoreCase(doctorNationalId);
//    }


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
    public ResponseEntity<MedicalRecords> updatePatientMedicalRecordsByPatientNationalId(String patientNationalId, zw.co.danhiko.medichronicle.dto.patient.Patient patient) {

        Optional<MedicalRecords> medicalRecords = medicalRecordsRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (medicalRecords.isEmpty()) {
            throw new FileDoesNotExistException("patient does not exist");
        } else {
            MedicalRecords medicalRecords1 = medicalRecords.get();
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
    public List<MedicalRecords> getPatientMedicalRecordsByDoctorNationalId(String patientNationalId) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request) {
        // Retrieve doctor, patient, and hospital entities from the database
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorNationalIdIgnoreCase(request.getDoctorNationalId());
        Optional<Patient> patientOptional = patientRepository.findByPatientNationalIdIgnoreCase(request.getPatientNationalId());
        Optional<Hospital> hospitalOptional = hospitalRepository.findByHospitalAddressIgnoreCase(request.getHospitalAddress());

        // Check if doctor, patient, and hospital entities exist
        if (doctorOptional.isEmpty() || patientOptional.isEmpty() || hospitalOptional.isEmpty()) {
            // Return appropriate error response if any of the entities is not found
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

        // Return the saved MedicalRecords object in the response
        return ResponseEntity.ok(medicalRecords);
    }

//    @Override
//    public ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request) {
//        // Retrieve doctor, patient, and hospital details from the database
//        DoctorAddress doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(request.getDoctorNationalId()).orElseThrow(() -> new RuntimeException("DoctorAddress not found"));
//        Patient patient = patientRepository.findByPatientNationalIdIgnoreCase(request.getPatientNationalId()).orElseThrow(() -> new RuntimeException("Patient not found"));
//        Hospital hospital = hospitalRepository.findByHospitalAddressIgnoreCase(request.getHospitalAddress()).orElseThrow(() -> new RuntimeException("Hospital not found"));
//
//        // Create a new medical record and set doctor, patient, and hospital details
//        MedicalRecords medicalRecord = new MedicalRecords();
//        medicalRecord.setDoctor(doctor);
//        medicalRecord.setPatient(patient);
//        medicalRecord.setHospital(hospital);
//        medicalRecord.setPatientName(patient.getPatientName());
//        medicalRecord.setChronicDisease(patient.getChronicDisease());
//        medicalRecord.setDayAdmitted(request.getDayAdmitted());
//      //  medicalRecord.setPrescription(request.getPrescription());
//        medicalRecord.setReferral(request.getReferral());
//        medicalRecord.setTemperature(request.getTemperature());
//        medicalRecord.setBp(request.getBp());
//        medicalRecord.setWeight(request.getWeight());
//        medicalRecord.setDiagnosis(request.getDiagnosis());

        //  .prescription(patientTreatmentRequest.getPrescription())
//                .dayAdmitted(request.getDayAdmitted())
//                .referral(patientTreatmentRequest.getReferral())
//                .temperature(patientTreatmentRequest.getTemperature())
//                .bp(patientTreatmentRequest.getBp())
//                .weight(patientTreatmentRequest.getWeight())
//                .diagnosis(patientTreatmentRequest.getDiagnosis())
        // Set other fields from the request

//        // Save the medical record to the database
//        System.out.println(medicalRecord.toString());
//        return ResponseEntity.ok(medicalRecordsRepository.save(medicalRecord));
//    }

//    @Override
//    public ResponseEntity<MedicalRecords> createPatientMedicalRecord(PatientTreatmentRequest patientTreatmentRequest,String hospitalAddress, String doctorNationalId, String patientNationalId) {
//
//        Optional<Patient> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//        if (patient.isEmpty()) {
//            throw new FileDoesNotExistException("patient does not exist");
//        }
//        Optional<DoctorAddress> doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
//        if (doctor.isEmpty()) {
//
//            throw new FileDoesNotExistException("doctor does not exist");
//
//        }
//        Optional<Hospital> hospital = hospitalRepository.findByHospitalAddressIgnoreCase(hospitalAddress);
//        if (hospital.isEmpty()) {
//            throw new FileDoesNotExistException("hospital does not exist");
//        }
//        MedicalRecords medicalRecords = MedicalRecords.builder()
//              //  .prescription(patientTreatmentRequest.getPrescription())
//                .dayAdmitted(patientTreatmentRequest.getDayAdmitted())
//                .referral(patientTreatmentRequest.getReferral())
//                .temperature(patientTreatmentRequest.getTemperature())
//                .bp(patientTreatmentRequest.getBp())
//                .weight(patientTreatmentRequest.getWeight())
//                .diagnosis(patientTreatmentRequest.getDiagnosis())
//                .build();
//
//
//            medicalRecordsRepository.save(medicalRecords);
//            return ResponseEntity.ok(medicalRecords);
//    }

    @Override
    public List<MedicalRecords> getAllPatientsMedicalRecords() {
        return medicalRecordsRepository.findAll();
    }


@Override
public ResponseEntity<MedicalRecords> deleteAllMedicalRecords() {

    medicalRecordsRepository.deleteAll();
    return ResponseEntity.ok().build();
}
}
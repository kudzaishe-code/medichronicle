package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;


import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.patient.Patient;
import zw.co.danhiko.medichronicle.dto.patient.PatientTreatmentRequest;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;
import zw.co.danhiko.medichronicle.repository.HospitalDetails.HospitalRepository;
import zw.co.danhiko.medichronicle.repository.MedicalRecords.MedicalRecordsRepository;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.DoctorNotFoundException;
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

        Optional<PatientDetails> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
        if (patient.isEmpty())
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
   // @Transactional
    public ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request) {

        System.out.println("kudzi");
        // Retrieve doctor, patient, and hospital details from the database
       DoctorDetails doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(request.getDoctorNationalId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
       PatientDetails patient = patientRepository.findByPatientNationalIdIgnoreCase(request.getPatientNationalId()).orElseThrow(() -> new RuntimeException("Patient not found"));
        HospitalDetails hospital = hospitalRepository.findByHospitalAddressIgnoreCase(request.getHospitalAddress()).orElseThrow(() -> new RuntimeException("Hospital not found"));
// create new medical record
        MedicalRecords medicalRecords = MedicalRecords.builder()
                .doctor(doctor)
                .patient(patient)
                .hospital(hospital)
                .patientName(patient.getPatientName())
                .chronicDisease(patient.getChronicDisease())
                .dayAdmitted(request.getDayAdmitted())
              //  .prescription(request.getPrescription())
                .referral(request.getReferral())
                .temperature(request.getTemperature())
                .bp(request.getBp())
                .weight(request.getWeight())
                .diagnosis(request.getDiagnosis())
                .build();
        System.out.println("kudzi");
      medicalRecords =  medicalRecordsRepository.save(medicalRecords);
        System.out.println("wdioofe");
           return ResponseEntity.ok(medicalRecords);

    }
//    @Override
//    public ResponseEntity<MedicalRecords> createMedicalRecord(PatientTreatmentRequest request) {
//        // Retrieve doctor, patient, and hospital details from the database
//        DoctorDetails doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(request.getDoctorNationalId()).orElseThrow(() -> new RuntimeException("Doctor not found"));
//        PatientDetails patient = patientRepository.findByPatientNationalIdIgnoreCase(request.getPatientNationalId()).orElseThrow(() -> new RuntimeException("Patient not found"));
//        HospitalDetails hospital = hospitalRepository.findByHospitalAddressIgnoreCase(request.getHospitalAddress()).orElseThrow(() -> new RuntimeException("Hospital not found"));
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
//        Optional<PatientDetails> patient = patientRepository.findByPatientNationalIdIgnoreCase(patientNationalId);
//        if (patient.isEmpty()) {
//            throw new FileDoesNotExistException("patient does not exist");
//        }
//        Optional<DoctorDetails> doctor = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId);
//        if (doctor.isEmpty()) {
//
//            throw new FileDoesNotExistException("doctor does not exist");
//
//        }
//        Optional<HospitalDetails> hospital = hospitalRepository.findByHospitalAddressIgnoreCase(hospitalAddress);
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
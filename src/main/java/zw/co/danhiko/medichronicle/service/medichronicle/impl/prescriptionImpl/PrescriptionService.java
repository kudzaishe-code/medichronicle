package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionService {



    List<String> createPrescription(String patientNationalId, String doctorNationalId, LocalDate medicalRecordCreationDate);

    Prescription getPrescriptionById(String patientNationalId);

    void deletePrescriptionByPatientNationalId(String patientNationalId);
    Prescription updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO);
    boolean isMedicationProvided(Long prescriptionId);


    Prescription updatePrescriptionById(String patientNationalId, PrescriptionDTO prescriptionDTO);

    boolean isMedicationProvided(String patientNationalId);

    List<Prescription> getPrescriptionsForPatient(String patientNationalId);
    // Other methods as needed
}
/// Other methods as needed



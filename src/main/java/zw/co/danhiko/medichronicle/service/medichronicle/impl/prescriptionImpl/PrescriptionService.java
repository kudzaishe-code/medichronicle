package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionService {



    List<PrescriptionDetails> createPrescription(String patientNationalId, String doctorNationalId, LocalDate medicalRecordCreationDate);

    PrescriptionDetails getPrescriptionById(String patientNationalId);

    void deletePrescriptionByPatientNationalId(String patientNationalId);
    PrescriptionDetails updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO);
    boolean isMedicationProvided(Long prescriptionId);


    PrescriptionDetails updatePrescriptionById(String patientNationalId, PrescriptionDTO prescriptionDTO);

    boolean isMedicationProvided(String patientNationalId);

    List<PrescriptionDetails> getPrescriptionsForPatient(String patientNationalId);
    // Other methods as needed
}
/// Other methods as needed



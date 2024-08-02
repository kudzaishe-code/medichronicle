package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import zw.co.danhiko.medichronicle.dto.MedicalRecord.MedicalRecordsDTO;
import zw.co.danhiko.medichronicle.dto.Pills.PillsDetailsDTO;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.time.LocalDate;
import java.util.List;

public interface PrescriptionService {





    Prescription getPrescriptionById(String patientNationalId);

    void deletePrescriptionByPatientNationalId(String patientNationalId);
   Prescription updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO);
    boolean isMedicationProvided(Long prescriptionId);

  Prescription updatePrescriptionById(String patientNationalId, PrescriptionDTO prescriptionDTO, Long prescriptionId);

    boolean isMedicationProvided(String patientNationalId);

   List<Prescription> getPrescriptionsForPatient(Long id);

    List<String> createPrescription(Long id);

    List<PillsDetailsDTO> parsePrescription(String prescription);



}
/// Other methods as needed




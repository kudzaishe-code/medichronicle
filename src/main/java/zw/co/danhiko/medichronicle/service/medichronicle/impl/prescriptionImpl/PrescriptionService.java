package zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl;

import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;

import java.util.List;

public interface PrescriptionService {




    List<PrescriptionDetails> createPrescription(PrescriptionDetails prescription, String patientNationalId, String doctorNationalId);

    PrescriptionDetails getPrescriptionById(String patientNationalId);

    void deletePrescriptionByPatientNationalId(String patientNationalId);



    PrescriptionDetails updatePrescriptionByPatientNationalId(String patientNationalId, PrescriptionDTO prescriptionDTO);


    boolean isMedicationProvided(Long prescriptionId);
    // Other methods as needed
}
/// Other methods as needed



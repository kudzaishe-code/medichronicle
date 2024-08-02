package zw.co.danhiko.medichronicle.service.medichronicle.impl.medicalRecordsImpl;

import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.util.List;

public class MedicalRecordResponse {
    private MedicalRecords medicalRecords;
    private List<PillsDetails> pills;

    public MedicalRecordResponse(MedicalRecords medicalRecords, List<PillsDetails> pills) {
        this.medicalRecords = medicalRecords;
        this.pills = pills;
    }

    // Getters and Setters
}

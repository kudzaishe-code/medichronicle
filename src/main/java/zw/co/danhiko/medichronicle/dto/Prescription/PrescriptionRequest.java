package zw.co.danhiko.medichronicle.dto.Prescription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PrescriptionRequest {
    private String prescription;
    private MedicalRecords medicalRecords;

    // Getters and Setters
}

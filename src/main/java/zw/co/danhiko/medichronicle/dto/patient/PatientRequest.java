package zw.co.danhiko.medichronicle.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PatientRequest {

    private String patientNationalId;

}

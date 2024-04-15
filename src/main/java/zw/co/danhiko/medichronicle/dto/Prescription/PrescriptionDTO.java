package zw.co.danhiko.medichronicle.dto.Prescription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PrescriptionDTO {
 private String prescription;
// private String token;


}
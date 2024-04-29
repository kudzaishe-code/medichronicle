package zw.co.danhiko.medichronicle.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;


import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PatientTreatmentRequest {
    private String patientNationalId;
    private BigDecimal pulse;
    private String hospitalAddress;
    private String doctorNationalId;
  //  private Prescription prescription;
    private Date dayAdmitted;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private BigDecimal weight;
    private String diagnosis;


}

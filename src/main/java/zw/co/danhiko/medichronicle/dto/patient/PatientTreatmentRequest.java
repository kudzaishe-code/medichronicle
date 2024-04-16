package zw.co.danhiko.medichronicle.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;


import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PatientTreatmentRequest {
    private String patientNationalId;
  //  private String patientName;
   // private String hospitalName;
   // private String chronicDisease;
  // private PrescriptionDetails prescription;
  //  private Date dayAdmitted;
   // private String referral;
  //  private BigDecimal temperature;
  //  private BigDecimal bp;
  //  private String doctorName;
   // private String doctorIdNumber;
  //  private BigDecimal weight;
 //   private String diagnosis;
 //   private BigDecimal height;
    private BigDecimal pulse;
    private String hospitalAddress;
    private String doctorNationalId;
    private PrescriptionDTO prescription;
    private Date dayAdmitted;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private BigDecimal weight;
    private String diagnosis;



}

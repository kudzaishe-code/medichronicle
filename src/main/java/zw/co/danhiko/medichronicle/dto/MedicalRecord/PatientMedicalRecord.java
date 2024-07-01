package zw.co.danhiko.medichronicle.dto.MedicalRecord;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PatientMedicalRecord {
    private String hospitalName;
    private String chronicDisease;
    private String prescription;
    private Date dayAdmitted;
    private Date dayDischarged;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private String medicationRequest;
    private BigDecimal weight;
    private String sickness;
    private String diagnosis;
    private BigDecimal pulse;



}

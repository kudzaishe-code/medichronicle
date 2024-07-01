package zw.co.danhiko.medichronicle.dto.patient;

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
public class PatientUpdateRequest {



    private String hospitalName;
    private String chronicDisease;
    private String prescription;
    private Date dayAdmitted;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private String doctorName;
    private String medicationRequest;
    private BigDecimal weight;


}

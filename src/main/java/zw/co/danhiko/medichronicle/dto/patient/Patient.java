package zw.co.danhiko.medichronicle.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Patient {
        private String patientName;
        private String patientNationalId;
        private String chronicDisease;
        private PrescriptionDetails prescription;
        private Date dayAdmitted;
        private Date dayDischarged;
        private String  referral;
        private BigDecimal temperature;
        private BigDecimal   bp;
        private BigDecimal weight;
}

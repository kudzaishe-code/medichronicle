package zw.co.danhiko.medichronicle.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "patients")
public class PatientDetails extends SecurityProperties.User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nationalId;
    private String patientName;
    private String hospitalName;
    private String chronicDisease;
    private String prescription;
    private Date dayAdmitted;
   private Date dayDischarged;
    private Date date;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private  String doctorName;
    private String doctorIdNumber;


}

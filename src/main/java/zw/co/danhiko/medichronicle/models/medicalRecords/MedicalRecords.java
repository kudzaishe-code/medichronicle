package zw.co.danhiko.medichronicle.models.medicalRecords;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;
import zw.co.danhiko.medichronicle.models.patient.Patient;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "medical")
public class MedicalRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  //  private String patientName;
  //  @Column(unique = true)
  //  private String patientNationalId;
  //  private String chronicDisease;
  //  @ManyToOne
//    @OneToOne(mappedBy =  "medicalRecords", cascade = CascadeType.ALL)
//    private Prescription prescription;
    private String prescription;
    private Date dayAdmitted;
    private String referral;
    private BigDecimal temperature;
    private BigDecimal bp;
    private String medicationRequest;
    private BigDecimal weight;
    private String sickness;
    private String diagnosis;
    private BigDecimal pulse;
    @Column(unique = true)
    private String doctorNationalId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}

package zw.co.danhiko.medichronicle.models.medicalRecords;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.PrescriptionDetails;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;

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
    private String patientName;
    @Column(unique = true)
    private String patientNationalId;
    private String chronicDisease;
    @ManyToOne
    private PrescriptionDetails prescription;
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
    private DoctorDetails doctor;

    @ManyToOne
    private PatientDetails patient;

    @ManyToOne
    private HospitalDetails hospital;
}

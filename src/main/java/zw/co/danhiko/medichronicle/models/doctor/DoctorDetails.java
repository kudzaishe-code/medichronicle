package zw.co.danhiko.medichronicle.models.doctor;

import jakarta.persistence.*;
import lombok.*;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.models.patient.PatientDetails;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "doctors")
public class DoctorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String doctorNationalId;
    private String doctorName;
    private String doctorPhoneNumber;
    private String location;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id") // Specify the foreign key column
    private HospitalDetails hospitalDetails; // Corrected property name

    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_National_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_National_id"))
    private Set<PatientDetails> patientsDetails = new HashSet<>();
    public void addPatientDetails(PatientDetails patientDetails) {
        patientsDetails.add(patientDetails);
        patientDetails.getDoctor().add(this);
    }
    // Other methods...
}
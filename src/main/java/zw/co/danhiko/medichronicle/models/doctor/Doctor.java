package zw.co.danhiko.medichronicle.models.doctor;

import jakarta.persistence.*;
import lombok.*;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;
import zw.co.danhiko.medichronicle.models.patient.Patient;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "doctors")
public class Doctor {
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
    private Hospital hospital; // Corrected property name

    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "doctor_patient",
            joinColumns = @JoinColumn(name = "doctor_National_id"),
            inverseJoinColumns = @JoinColumn(name = "patient_National_id"))
    private Set<Patient> patients = new HashSet<>();
    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.getDoctor().add(this);
    }
}
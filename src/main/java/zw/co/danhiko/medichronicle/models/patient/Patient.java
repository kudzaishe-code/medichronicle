package zw.co.danhiko.medichronicle.models.patient;

import jakarta.persistence.*;
import lombok.*;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String patientNationalId;
    private String chronicDisease;
    private String patientName;
    private String nationalId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "patient_doctor",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private Set<Doctor> doctor = new HashSet<>();

    // Other fields and methods...

    // Getter and setter methods...
}

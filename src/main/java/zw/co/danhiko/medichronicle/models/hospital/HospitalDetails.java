package zw.co.danhiko.medichronicle.models.hospital;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "hospital")
public class HospitalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String hospitalName;
    private String hospitalAddress;

    @OneToMany(mappedBy = "hospitalDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorDetails> doctors = new HashSet<>();
}

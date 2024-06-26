package zw.co.danhiko.medichronicle.models.Pharmacy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.danhiko.medichronicle.models.PrescriptionDetails.Prescription;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Entity(name = "pharmacy")
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pharmacyName;
    private String pharmacyAddress;
    private String pharmacyPhoneNumber;

    @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
    private Set<Prescription> prescriptions = new HashSet<>();
    public void setMedicationRequest(String string) {
    }

}

package zw.co.danhiko.medichronicle.repository.Pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyDetails, Long> {
    Optional<PharmacyDetails> findById(Long id);

    List<PharmacyDetails> findAll();
    void deleteById(Long id);


}

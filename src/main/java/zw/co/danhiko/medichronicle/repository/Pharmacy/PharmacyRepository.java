package zw.co.danhiko.medichronicle.repository.Pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyDetails, Long> {
    Optional<PharmacyDetails> findById(Long id);

    boolean existsByPharmacyAddress(String pharmacyAddress);

    ResponseEntity<PharmacyDetails> getPharmacyDetailsByPharmacyAddress(String pharmacyAddress);

    List<PharmacyDetails> findAll();

    void deleteById(Long id);

    Optional<PharmacyDetails> findByPharmacyAddress(String pharmacyAddress);

    void deleteByPharmacyAddress(String pharmacyAddress);
}

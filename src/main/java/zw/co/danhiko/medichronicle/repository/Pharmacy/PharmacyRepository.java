package zw.co.danhiko.medichronicle.repository.Pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.Pharmacy.Pharmacy;

import java.util.List;
import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    Optional<Pharmacy> findById(Long id);

    boolean existsByPharmacyAddress(String pharmacyAddress);

    ResponseEntity<Pharmacy> getPharmacyDetailsByPharmacyAddress(String pharmacyAddress);

    List<Pharmacy> findAll();

    void deleteById(Long id);

    Optional<Pharmacy> findByPharmacyAddress(String pharmacyAddress);

    void deleteByPharmacyAddress(String pharmacyAddress);
}

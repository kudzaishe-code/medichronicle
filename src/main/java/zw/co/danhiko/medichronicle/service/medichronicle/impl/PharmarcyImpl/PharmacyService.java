package zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyRegistration;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;

import java.util.List;

public interface PharmacyService {
    ResponseEntity<PharmacyDetails> createPharmacy(PharmacyRegistration pharmacy);

    ResponseEntity<PharmacyDetails> getPharmacyDetailsByPharmacyAddress(String PharmacyAddress);

    List<PharmacyDetails> updatePharmacy(String pharmacyAddress, PharmacyUpdateRequest pharmacy);
    void deletePharmacy(String pharmacyAddress);

    Page<PharmacyDetails> getAllPharmacies(Pageable pageable);

    // Other methods as needed
}

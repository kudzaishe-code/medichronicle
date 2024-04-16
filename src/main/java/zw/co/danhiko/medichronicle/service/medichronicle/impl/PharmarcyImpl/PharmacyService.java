package zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl;

import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyRegistration;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {
    ResponseEntity<PharmacyDetails> createPharmacy(PharmacyRegistration pharmacy);

    ResponseEntity<PharmacyDetails> getPharmacyDetailsByPharmacyAddress(String PharmacyAddress);

    List<PharmacyDetails> updatePharmacy(String pharmacyAddress, PharmacyUpdateRequest pharmacy);
    void deletePharmacy(String pharmacyAddress);
    // Other methods as needed
}

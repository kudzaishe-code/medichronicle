package zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl;

import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;

import java.util.List;

public interface PharmacyService {
    PharmacyDetails createPharmacy(PharmacyDetails pharmacy);
    PharmacyDetails getPharmacyById(Long id);
   List<PharmacyDetails> updatePharmacy(Long id, PharmacyUpdateRequest pharmacy);
    void deletePharmacy(Long id);
    // Other methods as needed
}

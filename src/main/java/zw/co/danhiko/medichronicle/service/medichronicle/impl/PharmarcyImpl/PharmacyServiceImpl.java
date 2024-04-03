package zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.repository.Pharmacy.PharmacyRepository;

import java.util.List;

@Data
@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;


    @Override
    public PharmacyDetails createPharmacy(PharmacyDetails pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public PharmacyDetails getPharmacyById(Long id) {
        return (PharmacyDetails) pharmacyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pharmacy not found with id: " + id));
    }

    @Override
    public List<PharmacyDetails> updatePharmacy(Long pharmacyId, PharmacyUpdateRequest updateRequest) {
        // Retrieve the pharmacy entity from the database
        PharmacyDetails pharmacy = pharmacyRepository.findById(pharmacyId)
                .orElseThrow(() -> new EntityNotFoundException("Pharmacy not found with id: " + pharmacyId));

        // Update the pharmacy details based on the update request
        if (updateRequest.getPharmacyName() != null) {
            pharmacy.setPharmacyName(updateRequest.getPharmacyName());
        }
        if (updateRequest.getPhoneNumber() != null) {
            pharmacy.setPhoneNumber(updateRequest.getPhoneNumber());
        }

        // Save the updated pharmacy entity
        pharmacyRepository.save(pharmacy);

        // Retrieve and return all pharmacies after the update
        return pharmacyRepository.findAll();
    }


    @Override
    public void deletePharmacy(Long id) {

        pharmacyRepository.deleteById(id);

    }

    // Implement other methods as needed
}

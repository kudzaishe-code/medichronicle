package zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyRegistration;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.repository.Pharmacy.PharmacyRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class PharmacyServiceImpl implements PharmacyService {
    private final PharmacyRepository pharmacyRepository;
    @Override
    public ResponseEntity<PharmacyDetails> createPharmacy(PharmacyRegistration pharmacy) {
        if (pharmacyRepository.existsByPharmacyAddress(pharmacy.getPharmacyAddress()))
            throw new RuntimeException("pharmacy already exists");
             PharmacyDetails pharmacyDetails = PharmacyDetails.builder()
                     .pharmacyName(pharmacy.getPharmacyName())
                     .pharmacyAddress(pharmacy.getPharmacyAddress())
                     .pharmacyPhoneNumber(pharmacy.getPharmacyPhoneNumber())
                     .build();
             pharmacyDetails = pharmacyRepository.save(pharmacyDetails);
            return ResponseEntity.ok(pharmacyDetails);

    }

    @Override
    public ResponseEntity<PharmacyDetails> getPharmacyDetailsByPharmacyAddress(String pharmacyAddress) {
        if(!pharmacyRepository.existsByPharmacyAddress(pharmacyAddress))
            throw  new RuntimeException("pharmacy does not exist");
       return ResponseEntity.ok(pharmacyRepository.getPharmacyDetailsByPharmacyAddress(pharmacyAddress).getBody());


    }
    @Override
    public List<PharmacyDetails> updatePharmacy(String pharmacyAddress, PharmacyUpdateRequest pharmacyUpdateRequest) {
        // Check if the pharmacy exists
        Optional<PharmacyDetails> pharmacyDetails = pharmacyRepository.findByPharmacyAddress(pharmacyAddress);
        if (pharmacyDetails == null) {
            throw new RuntimeException("Pharmacy with address " + pharmacyAddress + " does not exist");
        }

        // Update the pharmacy details
        pharmacyDetails.get().setPharmacyName(pharmacyUpdateRequest.getPharmacyName());
        pharmacyDetails.get().setPharmacyPhoneNumber(pharmacyUpdateRequest.getPharmacyPhoneNumber());
        // Save the updated pharmacy details
       // return pharmacyRepository.save(pharmacyDetails);
        return Collections.singletonList(pharmacyRepository.save(pharmacyDetails.get()));
    }

    @Override
    public void deletePharmacy(String pharmacyAddress) {

        pharmacyRepository.deleteByPharmacyAddress(pharmacyAddress);
    }

    @Override
    public Page<PharmacyDetails> getAllPharmacies(Pageable pageable) {
       return pharmacyRepository.findAll(pageable);
    }


}

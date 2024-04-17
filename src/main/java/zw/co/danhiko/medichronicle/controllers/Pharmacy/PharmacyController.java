package zw.co.danhiko.medichronicle.controllers.Pharmacy;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyRegistration;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl.PharmacyService;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;


    @PostMapping("/create")
    public ResponseEntity<PharmacyDetails> createPharmacy(@RequestBody PharmacyRegistration pharmacy) {
       return pharmacyService.createPharmacy(pharmacy);

    }
@GetMapping("/get-pharmacy-details-by-pharmacyAddress/{pharmacyAddress}")
public ResponseEntity<PharmacyDetails> getPharmacyDetailsByPharmacyAddress(@PathVariable String pharmacyAddress) {

    return pharmacyService.getPharmacyDetailsByPharmacyAddress(pharmacyAddress);

}
    @PutMapping("/update-pharmacy-by-pharmacyAddress/{pharmacyAddress}")
    public List<PharmacyDetails> updatePharmacyByPharmacyAddress(@PathVariable String pharmacyAddress, @RequestBody PharmacyUpdateRequest pharmacy) {
        return pharmacyService.updatePharmacy(pharmacyAddress, pharmacy);
    }
    @DeleteMapping("/delete-pharmacy-by-pharmacyAddress/{pharmacyAddress}")
    public void deletePharmacy(@PathVariable String pharmacyAddress) {
        pharmacyService.deletePharmacy(pharmacyAddress);
    }
    @GetMapping
    public List<PharmacyDetails> getAllPharmacies() {
        return pharmacyService.getAll();
    }
}

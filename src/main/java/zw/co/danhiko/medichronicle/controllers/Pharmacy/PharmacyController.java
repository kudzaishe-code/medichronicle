package zw.co.danhiko.medichronicle.controllers.Pharmacy;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.PharmacyDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl.PharmacyService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;


    @PostMapping("/create")
    public Optional<PharmacyDetails> createPharmacy(@RequestBody PharmacyDetails pharmacy) {
        PharmacyDetails createdPharmacy = pharmacyService.createPharmacy(pharmacy);
        return Optional.of(createdPharmacy);
    }

    @GetMapping("/get-pharmacy-by-id{id}")
    public ResponseEntity<PharmacyDetails> getPharmacyById(@PathVariable Long id) {
        PharmacyDetails pharmacy = pharmacyService.getPharmacyById(id);
        return new ResponseEntity<>(pharmacy, HttpStatus.OK);
    }

//update pharmacy
    @PutMapping("/update-pharmacy-by-id/{id}")
    public List<PharmacyDetails> updatePharmacy(@PathVariable Long id, @RequestBody PharmacyUpdateRequest pharmacy) {
        return pharmacyService.updatePharmacy(id, pharmacy);
    }
    @DeleteMapping("/delete-pharmacy-by-id/{id}")
    public void deletePharmacy(@PathVariable Long id) {
        pharmacyService.deletePharmacy(id);
    }
}

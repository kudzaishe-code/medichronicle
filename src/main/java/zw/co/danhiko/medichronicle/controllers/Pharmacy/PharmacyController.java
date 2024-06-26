package zw.co.danhiko.medichronicle.controllers.Pharmacy;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyRegistration;
import zw.co.danhiko.medichronicle.dto.Pharmacy.PharmacyUpdateRequest;
import zw.co.danhiko.medichronicle.models.Pharmacy.Pharmacy;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.PharmarcyImpl.PharmacyService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;


    @PostMapping("/create")
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody PharmacyRegistration pharmacy) {
       return pharmacyService.createPharmacy(pharmacy);

    }
@GetMapping("/get-pharmacy-details-by-pharmacyAddress/{pharmacyAddress}")
public ResponseEntity<Pharmacy> getPharmacyDetailsByPharmacyAddress(@PathVariable String pharmacyAddress) {

    return pharmacyService.getPharmacyDetailsByPharmacyAddress(pharmacyAddress);

}
    @PutMapping("/update-pharmacy-by-pharmacyAddress/{pharmacyAddress}")
    public List<Pharmacy> updatePharmacyByPharmacyAddress(@PathVariable String pharmacyAddress, @RequestBody PharmacyUpdateRequest pharmacy) {
        return pharmacyService.updatePharmacy(pharmacyAddress, pharmacy);
    }
    @DeleteMapping("/delete-pharmacy-by-pharmacyAddress/{pharmacyAddress}")
    public void deletePharmacy(@PathVariable String pharmacyAddress) {
        pharmacyService.deletePharmacy(pharmacyAddress);
    }
    @GetMapping("/get-all-pharmacies")
    public Page<Pharmacy> getAllPharmacies(@RequestParam (defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return pharmacyService.getAllPharmacies(pageable);
    }
}

package zw.co.danhiko.medichronicle.controllers.Hospital;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital.HospitalService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hospitals")
@CrossOrigin
public class HospitalController {
    @Autowired
    private  HospitalService hospitalService;
@GetMapping("/get-hospital-details")
    public Optional<HospitalDetails> findHospitalDetails(@PathVariable String hospitalAddress) {
        return hospitalService.findHospitalDetailsByAddress(hospitalAddress);

    }
    // find hospital by name
    @GetMapping("/find-hospital-by-name")
    public ResponseEntity<HospitalDetails> findHospitalByName(@RequestParam String hospitalName) {
        return hospitalService.findHospitalByNameIgnoreCase(hospitalName);
    }
    @GetMapping("/get-all-hospitals")
    public Page<HospitalDetails> getAllHospitals(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return  hospitalService.getAllHospitalsByName(pageable);
    }
    @PostMapping("/create-hospital")
    public ResponseEntity<HospitalDetails> createHospital(HospitalRequest hospitalRequest) {
        return hospitalService.createHospital(hospitalRequest);
    }

    @PutMapping("/update-hospital")
    public ResponseEntity<HospitalDetails> updateHospital(@PathVariable Long Id, HospitalDetails hospitalDetails) {
        return hospitalService.updateHospital(hospitalDetails, Id);
    }
@DeleteMapping("/delete-hospital")
    public ResponseEntity<HospitalDetails> deleteHospital(String hospitalAddress) {
        return hospitalService.deleteHospital(hospitalAddress);
    }

}

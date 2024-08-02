package zw.co.danhiko.medichronicle.controllers.Hospital;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalUpdateRequest;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital.HospitalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/hospitals")
@CrossOrigin
public class HospitalController {
    @Autowired
    private  HospitalService hospitalService;
@GetMapping("/get-hospital-details-by-address/{hospitalAddress}")
    public Optional<Hospital> findHospitalDetails(@PathVariable String hospitalAddress) {
        return hospitalService.findHospitalDetailsByAddress(hospitalAddress);

    }

    @GetMapping("/find-hospital-by-name")
    public List<Hospital> findHospitalByName(@RequestParam String hospitalName) {
        return hospitalService.findHospitalByNameIgnoreCase(hospitalName);
    }
    @GetMapping("/get-all-hospitals")
    public Page<Hospital> getAllHospitals(@RequestParam(defaultValue = "0") Integer pageNumber,
                                          @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return  hospitalService.getAllHospitalsByName(pageable);
    }
    @PostMapping("/create-hospital")
    public ResponseEntity<Hospital> createHospital(HospitalRequest hospitalRequest) {
        return hospitalService.createHospital(hospitalRequest);
    }
@DeleteMapping("/delete-hospital")
    public ResponseEntity<Hospital> deleteHospital(String hospitalAddress) {
        return hospitalService.deleteHospital(hospitalAddress);
    }
    //hospital update
    @PutMapping("/update-hospital")
    public ResponseEntity<Hospital> hospitalUpdate(@RequestBody HospitalUpdateRequest hospitalUpdateRequest, String hospitalAddress) {
        return hospitalService.hospitalUpdate(hospitalUpdateRequest, hospitalAddress);
    }

}

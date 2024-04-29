package zw.co.danhiko.medichronicle.controllers.Hospital;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
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
@GetMapping("/get-hospital-details")
    public Optional<Hospital> findHospitalDetails(@PathVariable String hospitalAddress) {
        return hospitalService.findHospitalDetailsByAddress(hospitalAddress);

    }
    // find hospital by name
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

//    @PutMapping("/update-hospital")
//    public ResponseEntity<Hospital> updateHospital(@PathVariable Long Id, Hospital hospital) {
//        return hospitalService.updateHospital(hospital, Id);
//    }
@DeleteMapping("/delete-hospital")
    public ResponseEntity<Hospital> deleteHospital(String hospitalAddress) {
        return hospitalService.deleteHospital(hospitalAddress);
    }

}

package zw.co.danhiko.medichronicle.controllers.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl.DoctorRegistration;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl.DoctorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctors")
@CrossOrigin
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping("/get-all-Doctors")
    public Page<DoctorDetails> getAllDoctors(@RequestParam(defaultValue = "0") Integer pageNumber,
                                             @RequestParam(defaultValue = "10") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return doctorService.getAllDoctors(pageable);
    }
    @GetMapping("/get-doctor-details-by-doctor-nationalId/{doctorNationalId}")
    public ResponseEntity<DoctorDetails> getDoctorDetailsByDoctorNationalId(@PathVariable String doctorNationalId) {
        return doctorService.getDoctorDetailsByDoctorNationalIdIgnoreCase(doctorNationalId);
    }
    @PostMapping("/create-doctor")
    public ResponseEntity<DoctorDetails> createDoctor(@RequestBody DoctorRegistration request) {
        return doctorService.addDoctor(request);
    }

    @PutMapping("/update/{doctorNationalId}")
    public ResponseEntity<DoctorDetails>  updateDoctorDetailsByDoctorNationalIdIgnoreCase(@PathVariable String doctorNationalId ,
                                                                                 @RequestBody Doctor doctor) {
        return doctorService.updateDoctorDetailsByDoctorNationalIdIgnoreCase(doctorNationalId, doctor);

    }
    @DeleteMapping("/delete/{doctorNationalId}")
    public DoctorDetails deleteDoctor(@PathVariable String doctorNationalId) {
        return doctorService.deleteDoctor(doctorNationalId);
    }

}

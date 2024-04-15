package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;


public interface DoctorService {
    Page<DoctorDetails> getAllDoctors(Pageable pageable);
    ResponseEntity<DoctorDetails> getDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId);
    ResponseEntity<DoctorDetails> addDoctor(DoctorRegistration request);
    ResponseEntity<DoctorDetails> updateDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId, Doctor doctor);;
    DoctorDetails deleteDoctor(String doctorNationalId);



}

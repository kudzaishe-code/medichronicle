package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.doctor.DoctorAddress;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;


public interface DoctorService {
    Page<Doctor> getAllDoctors(Pageable pageable);
    ResponseEntity<Doctor> getDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId);
    ResponseEntity<Doctor> addDoctor(DoctorRegistration request);
    ResponseEntity<Doctor> updateDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId, DoctorAddress doctor);;
    Doctor deleteDoctor(String doctorNationalId);



}

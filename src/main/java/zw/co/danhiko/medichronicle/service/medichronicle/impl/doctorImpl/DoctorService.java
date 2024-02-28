package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import zw.co.danhiko.medichronicle.dto.doctor.DoctorUpdateRequest;
import zw.co.danhiko.medichronicle.dto.patient.PatientUpdateRequest;
import zw.co.danhiko.medichronicle.models.DoctorDetails;
import zw.co.danhiko.medichronicle.models.PatientDetails;
import zw.co.danhiko.medichronicle.service.medichronicle.impl.patientImpl.PatientRegistration;

import java.util.Date;


public interface DoctorService {
    Page<DoctorDetails> getAllDoctors(Pageable pageable);


    ResponseEntity<DoctorDetails> getDoctorDetailsByDoctorNationalId(String doctorNationalId);

    ResponseEntity<DoctorDetails> createDoctor(DoctorRegistration request);

    ResponseEntity<DoctorDetails> updateDoctorDetailsByDoctorNationalId(String doctorNationalId, DoctorUpdateRequest request);

   DoctorDetails deleteDoctor(String doctorNationalId);

}

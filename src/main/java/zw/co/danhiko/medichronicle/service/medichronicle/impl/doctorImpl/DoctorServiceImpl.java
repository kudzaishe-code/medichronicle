package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.doctor.Doctor;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.repository.patient.PatientRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Page<DoctorDetails> getAllDoctors(Pageable pageable) {

        return doctorRepository.findAll(pageable);
    }
    @Override
    public ResponseEntity<DoctorDetails> getDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId) {

        Optional<DoctorDetails> doctorDetails= doctorRepository.findByDoctorNationalIdIgnoreCase( doctorNationalId);
        if (doctorDetails.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");
        return ResponseEntity.ok(doctorDetails.get());
        }

@Override
    public ResponseEntity<DoctorDetails> addDoctor(DoctorRegistration request) {
        if (doctorRepository.existsByDoctorNationalIdIgnoreCase(request.getDoctorNationalId()))
            throw new RecordAlreadyExistException("doctor with id already exist");


        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.setDoctorNationalId(request.getDoctorNationalId());
        doctorDetails.setDoctorName(request.getDoctorName());
        doctorDetails.setDoctorPhoneNumber(request.getDoctorPhoneNumber());
        doctorDetails.setLocation(request.getLocation());
        DoctorDetails savedDoctor = doctorRepository.save(doctorDetails);
        return ResponseEntity.ok(savedDoctor);
    }

    @Override
    public ResponseEntity<DoctorDetails> updateDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId, Doctor doctor) {

     //logic to  update doctor details
        if(doctorRepository.existsByDoctorNationalIdIgnoreCase(doctorNationalId));
        DoctorDetails doctorDetails = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId).get();
        doctorDetails.setLocation(doctorDetails.getLocation());
        doctorDetails = doctorRepository.save(doctorDetails);
        return ResponseEntity.ok(doctorDetails);
//       if (doctorRepository.existsByDoctorNationalIdIgnoreCase(doctorNationalId));
//       DoctorDetails doctorDetails = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId).get();
//       doctorDetails.setHospitalName(doctor.getHospitalName());
//       doctorDetails = doctorRepository.save(doctorDetails);
//       return ResponseEntity.ok(doctorDetails);
    }



    @Override
    public DoctorDetails deleteDoctor(String DoctorNationalId) {

        Optional<DoctorDetails> doctor= doctorRepository.findByDoctorNationalIdIgnoreCase(DoctorNationalId);
        if(doctor.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");

        doctorRepository.delete(doctor.get());
        return  doctor.get();
    }

}
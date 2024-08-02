package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.doctor.DoctorAddress;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;

import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    @Override
    public Page<Doctor> getAllDoctors(Pageable pageable) {

        return doctorRepository.findAll(pageable);
    }
    @Override
    public ResponseEntity<Doctor> getDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId) {
        Optional<Doctor> doctorDetails= doctorRepository.findByDoctorNationalIdIgnoreCase( doctorNationalId);
        if (doctorDetails.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");
        return ResponseEntity.ok(doctorDetails.get());
        }

@Override
    public ResponseEntity<Doctor> addDoctor(DoctorRegistration request) {
        if (doctorRepository.existsByDoctorNationalIdIgnoreCase(request.getDoctorNationalId()))
            throw new RecordAlreadyExistException("doctor with id already exist");


        Doctor doctor = new Doctor();
        doctor.setDoctorNationalId(request.getDoctorNationalId());
        doctor.setDoctorName(request.getDoctorName());
        doctor.setDoctorPhoneNumber(request.getDoctorPhoneNumber());
        doctor.setLocation(request.getLocation());
        Doctor savedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(savedDoctor);
    }

    @Override
    public ResponseEntity<Doctor> updateDoctorDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId, DoctorAddress doctor) {

     //logic to  update doctor details
        if(doctorRepository.existsByDoctorNationalIdIgnoreCase(doctorNationalId));
        Doctor doctorDetails = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId).get();
        doctorDetails.setLocation(doctorDetails.getLocation());
        doctorDetails = doctorRepository.save(doctorDetails);
        return ResponseEntity.ok(doctorDetails);
//       if (doctorRepository.existsByDoctorNationalIdIgnoreCase(doctorNationalId));
//       DoctorAddress doctorDetails = doctorRepository.findByDoctorNationalIdIgnoreCase(doctorNationalId).get();
//       doctorDetails.setHospitalName(doctor.getHospitalName());
//       doctorDetails = doctorRepository.save(doctorDetails);
//       return ResponseEntity.ok(doctorDetails);
    }



    @Override
    public Doctor deleteDoctor(String DoctorNationalId) {

        Optional<Doctor> doctor= doctorRepository.findByDoctorNationalIdIgnoreCase(DoctorNationalId);
        if(doctor.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");

        doctorRepository.delete(doctor.get());
        return  doctor.get();
    }

}
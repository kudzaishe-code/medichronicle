package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.doctor.DoctorUpdateRequest;
import zw.co.danhiko.medichronicle.models.DoctorDetails;
import zw.co.danhiko.medichronicle.repository.doctor.DoctorRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.FileDoesNotExistException;

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
    public ResponseEntity<DoctorDetails> getDoctorDetailsByDoctorNationalId(String doctorNationalId) {

        Optional<DoctorDetails> doctorDetails= doctorRepository.findByDoctorNationalId( doctorNationalId);
        if (doctorDetails.isEmpty())
            throw new FileDoesNotExistException("doctor does not exist");
        return ResponseEntity.ok(doctorDetails.get());
        }


    @Override
    public ResponseEntity<DoctorDetails> createDoctor(DoctorRegistration request) {
        return null;
    }

    @Override
    public ResponseEntity<DoctorDetails> updateDoctorDetailsByDoctorNationalId(String doctorNationalId, DoctorUpdateRequest request) {
        return null;
    }

    @Override
    public DoctorDetails deleteDoctor(String DoctorNationalId) {
        return null;
    }
}

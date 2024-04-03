package zw.co.danhiko.medichronicle.repository.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.dto.Prescription.PrescriptionDTO;
import zw.co.danhiko.medichronicle.models.doctor.DoctorDetails;

import java.util.List;
import java.util.Optional;
@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails, Long> {
        Optional<DoctorDetails> findByDoctorNationalIdIgnoreCase(String doctorNationalId);
        List<DoctorDetails>getAllPatientDetailsByDoctorNationalIdIgnoreCase(String doctorNationalId);

boolean existsByDoctorNationalIdIgnoreCase(String doctorNationalId);



}



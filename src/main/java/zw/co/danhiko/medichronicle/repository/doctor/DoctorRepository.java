package zw.co.danhiko.medichronicle.repository.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.danhiko.medichronicle.models.DoctorDetails;
import zw.co.danhiko.medichronicle.models.PatientDetails;

import java.util.Date;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorDetails, Long> {
        Optional<DoctorDetails>findByDoctorNationalId(String doctorNationalId);

boolean existsDoctorByDoctorNationalId(String doctorNationalId);
        }



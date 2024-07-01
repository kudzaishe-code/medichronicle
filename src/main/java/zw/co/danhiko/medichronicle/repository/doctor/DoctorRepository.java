package zw.co.danhiko.medichronicle.repository.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.doctor.Doctor;

import java.util.Optional;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
        Optional<Doctor> findByDoctorNationalIdIgnoreCase(String doctorNationalId);

boolean existsByDoctorNationalIdIgnoreCase(String doctorNationalId);



}



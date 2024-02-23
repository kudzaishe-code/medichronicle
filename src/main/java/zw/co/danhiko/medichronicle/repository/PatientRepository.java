package zw.co.danhiko.medichronicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.PatientDetails;


import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails, Long> {
    Optional <PatientDetails> findByNationalId(String nationalId);

    boolean existsByNationalId(String nationalId);
}

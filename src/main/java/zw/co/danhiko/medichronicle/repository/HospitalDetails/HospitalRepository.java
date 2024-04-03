package zw.co.danhiko.medichronicle.repository.HospitalDetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;

import java.util.List;
import java.util.Optional;
@Repository
public interface HospitalRepository extends JpaRepository<HospitalDetails, Long> {

    Optional<HospitalDetails> findById(Long id);

 void deleteHospitalById( Long id);
 List<HospitalDetails> findHospitalDetailsByHospitalName(String hospitalName);
 boolean existsByHospitalAddress(String hospitalAddress);


    Optional<HospitalDetails> findByHospitalAddress(String hospitalAddress);

    void deleteHospitalByHospitalAddress(String hospitalAddress);
}


package zw.co.danhiko.medichronicle.repository.HospitalDetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;

import java.util.List;
import java.util.Optional;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {

    Optional<Hospital> findById(Long id);

 void deleteHospitalById( Long id);
 List<Hospital> findHospitalDetailsByHospitalName(String hospitalName);
 boolean existsByHospitalAddress(String hospitalAddress);

   Optional<Hospital> findByHospitalAddressIgnoreCase(String hospitalAddress);

    Optional<Hospital> findByHospitalAddress(String hospitalAddress);


    boolean existsByHospitalNameIgnoreCase(String hospitalName);

    List<Hospital> findByHospitalNameIgnoreCase(String hospitalName);

    void deleteByHospitalAddress(String hospitalAddress);
}


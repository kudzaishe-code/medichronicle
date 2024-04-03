package zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;

import java.util.Optional;

@Service
public interface HospitalService {
    Page<HospitalDetails> getAllHospitalsByName(Pageable pageable);
    Optional<HospitalDetails> findHospitalDetailsByAddress(String hospitalAddress);
    ResponseEntity<HospitalDetails> createHospital(HospitalRequest hospitalRequest);
    ResponseEntity<HospitalDetails> updateHospital(HospitalDetails hospitalDetails, Long id);
    ResponseEntity<HospitalDetails> deleteHospital(String hospitalAddress);
    ResponseEntity<HospitalDetails> findHospitalByNameIgnoreCase(String hospitalName);

}

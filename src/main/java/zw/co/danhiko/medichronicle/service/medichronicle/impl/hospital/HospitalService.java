package zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalUpdateRequest;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;

import java.util.List;
import java.util.Optional;

@Service
public interface HospitalService {
    Page<HospitalDetails> getAllHospitalsByName(Pageable pageable);
    Optional<HospitalDetails> findHospitalDetailsByAddress(String hospitalAddress);
    ResponseEntity<HospitalDetails> createHospital(HospitalRequest hospitalRequest);
    ResponseEntity<HospitalDetails> deleteHospital(String hospitalAddress);
    List<HospitalDetails> findHospitalByNameIgnoreCase(String hospitalName);
    ResponseEntity<HospitalDetails>hospitalUpdate(HospitalUpdateRequest hospitalUpdateRequest);

    ResponseEntity<HospitalDetails> hospitalUpdate(HospitalUpdateRequest hospitalUpdateRequest, String hospitalAddress);
}

package zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalUpdateRequest;
import zw.co.danhiko.medichronicle.models.hospital.Hospital;

import java.util.List;
import java.util.Optional;

@Service
public interface HospitalService {
    Page<Hospital> getAllHospitalsByName(Pageable pageable);
    Optional<Hospital> findHospitalDetailsByAddress(String hospitalAddress);
    ResponseEntity<Hospital> createHospital(HospitalRequest hospitalRequest);
    ResponseEntity<Hospital> deleteHospital(String hospitalAddress);
    List<Hospital> findHospitalByNameIgnoreCase(String hospitalName);
    ResponseEntity<Hospital> hospitalUpdate(HospitalUpdateRequest hospitalUpdateRequest, String hospitalAddress);
}

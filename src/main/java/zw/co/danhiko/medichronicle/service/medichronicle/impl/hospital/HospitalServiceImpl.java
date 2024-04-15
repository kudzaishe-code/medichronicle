package zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalRequest;
import zw.co.danhiko.medichronicle.dto.hospital.HospitalUpdateRequest;
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.repository.HospitalDetails.HospitalRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service
@Transactional

public class HospitalServiceImpl implements HospitalService {
    private final HospitalRepository hospitalRepository;

    @Override
    public Page<HospitalDetails> getAllHospitalsByName(Pageable pageable) {
        // Create a Sort object to sort by hospital name in ascending order
        Sort sort = Sort.by(Sort.Direction.ASC, "hospitalName");

        // Apply sorting to the pageable object
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        // Retrieve hospitals sorted by name
        return hospitalRepository.findAll(sortedPageable);
    }

    @Override
    public Optional<HospitalDetails> findHospitalDetailsByAddress(String hospitalAddress) {
        if (!hospitalRepository.existsByHospitalAddress(hospitalAddress)) {
            return Optional.empty();
        }
        return hospitalRepository.findByHospitalAddress(hospitalAddress);
    }

    @Override
    public ResponseEntity<HospitalDetails> createHospital(HospitalRequest hospitalRequest) {
        if (hospitalRepository.existsByHospitalAddress(hospitalRequest.getHospitalAddress()))
            throw new RecordAlreadyExistException("hospital with address already exist");

        HospitalDetails hospitalDetails = HospitalDetails.builder()
                .hospitalName(hospitalRequest.getHospitalName())
                .hospitalAddress(hospitalRequest.getHospitalAddress())
                .build();
        hospitalDetails = hospitalRepository.save(hospitalDetails);
        // Assuming HttpStatus.CREATED is appropriate for successful creation
        return new ResponseEntity<>(hospitalDetails, HttpStatus.CREATED);
    }




//    @Override
//    public ResponseEntity<HospitalDetails> updateHospital(HospitalDetails request, Long id) {
//        if (!hospitalRepository.existsById(id)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        // Assuming hospitalRepository.build() returns the updated hospitalDetails
//        HospitalDetails hospitalDetails = HospitalDetails.builder()
//                .hospitalName(request.getHospitalName())
//                .hospitalAddress(request.getHospitalAddress())
//                .build();
//        hospitalDetails = hospitalRepository.save(hospitalDetails);
//        return new ResponseEntity<>(hospitalDetails, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<HospitalDetails> deleteHospital(String hospitalAddress) {
   //delete hospital
        if (!hospitalRepository.existsByHospitalAddress(hospitalAddress)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        hospitalRepository.deleteByHospitalAddress(hospitalAddress);
        return ResponseEntity.noContent().build();
        }


    @Override
    public List<HospitalDetails> findHospitalByNameIgnoreCase(String hospitalName) {
        // find hospital by name
        if (!hospitalRepository.existsByHospitalNameIgnoreCase(hospitalName)) {
            return Collections.emptyList();
        }
        return hospitalRepository.findByHospitalNameIgnoreCase(hospitalName);
    }

    @Override
    public ResponseEntity<HospitalDetails> hospitalUpdate(HospitalUpdateRequest hospitalUpdateRequest, String hospitalAddress) {
        if (hospitalRepository.existsByHospitalAddress(hospitalAddress)){
            Optional<HospitalDetails> hospitalDetails = hospitalRepository.findByHospitalAddress(hospitalAddress);
            hospitalDetails.setHospitalContact(hospitalUpdateRequest.getHospitalContact());
            hospitalRepository.save(hospitalDetails);
            return ResponseEntity.ok(hospitalDetails);

        }
    }
}




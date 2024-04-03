package zw.co.danhiko.medichronicle.service.medichronicle.impl.hospital;

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
import zw.co.danhiko.medichronicle.models.hospital.HospitalDetails;
import zw.co.danhiko.medichronicle.repository.HospitalDetails.HospitalRepository;
import zw.co.danhiko.medichronicle.service.medichronicle.exceptions.RecordAlreadyExistException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
@Service

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

        // Assuming HttpStatus.CREATED is appropriate for successful creation
        return new ResponseEntity<>(hospitalDetails, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<HospitalDetails> updateHospital(HospitalDetails request, Long id) {
        if (!hospitalRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // Assuming hospitalRepository.build() returns the updated hospitalDetails
        HospitalDetails hospitalDetails = HospitalDetails.builder()
                .hospitalName(request.getHospitalName())
                .hospitalAddress(request.getHospitalAddress())
                .build();
        // Assuming HttpStatus.OK is appropriate for successful update
        return new ResponseEntity<>(hospitalDetails, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HospitalDetails> deleteHospital(String hospitalAddress) {
        if (!hospitalRepository.existsByHospitalAddress(hospitalAddress)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        hospitalRepository.deleteHospitalByHospitalAddress(hospitalAddress);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<HospitalDetails> findHospitalByNameIgnoreCase(String hospitalName) {
        if (hospitalName == null || hospitalName.isEmpty()) {
            // Return an empty list if name is null or empty
            return ResponseEntity.ok((HospitalDetails) Collections.emptyList());
        }

        List<HospitalDetails> hospitals = hospitalRepository.findHospitalDetailsByHospitalName(hospitalName);

        // Handle the case where no hospitals are found with the given name
        if (hospitals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok((HospitalDetails) hospitals);
    }
}




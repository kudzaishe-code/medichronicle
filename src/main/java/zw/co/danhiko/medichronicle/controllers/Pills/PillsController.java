package zw.co.danhiko.medichronicle.controllers.Pills;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.danhiko.medichronicle.dto.Pills.PillsDetailsDTO;

import zw.co.danhiko.medichronicle.service.medichronicle.impl.prescriptionImpl.PrescriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/medicines")
@CrossOrigin
public class PillsController {

    private final PrescriptionService prescriptionService;

@PostMapping("/parse")
public ResponseEntity<List<PillsDetailsDTO>> parsePrescription(@RequestBody String prescription) {
    List<PillsDetailsDTO> pillsDetails = prescriptionService.parsePrescription(prescription);
    return ResponseEntity.ok(pillsDetails);
}
}
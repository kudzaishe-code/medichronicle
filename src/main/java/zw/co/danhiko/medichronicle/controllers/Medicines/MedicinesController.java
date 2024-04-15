package zw.co.danhiko.medichronicle.controllers.Medicines;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/medicines")
@CrossOrigin
public class MedicinesController {
    // get all medicines
    @GetMapping("/get-all-medicines")
    public String getAllMedicines() {
        return "All Medicines";
    }
}

package zw.co.danhiko.medichronicle.service.medichronicle.impl.Pills;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.util.ArrayList;
import java.util.List;
@Data
@RequiredArgsConstructor
@Service
public class PillsServiceImpl implements PillsService {



    public List<PillsDetails> parsePrescription(String prescription, MedicalRecords medicalRecords) {
        List<PillsDetails> pills = new ArrayList<>();
        String[] pillDetails = prescription.split(","); // Splitting the prescription into individual pill details

        for (String detail : pillDetails) {
            String[] parts = detail.trim().split(" "); // Splitting each pill detail into its components
            if (parts.length == 3) {
                String name = parts[0];
                String dosage = parts[1];
                String frequency = parts[2];

                // Creating a Pill object with the extracted components
                PillsDetails pill = PillsDetails.builder()
                        .name(name)
                        .dosage(dosage)
                        .frequency(frequency)
                       // .medicalRecords(medicalRecords) // Associating the Pill with the MedicalRecords
                        .build();
                pills.add(pill);
            }
        }

        return pills; // Returning the list of created Pill objects
    }

}

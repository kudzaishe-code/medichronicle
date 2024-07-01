package zw.co.danhiko.medichronicle.dto.Pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PharmacyRegistration {
    private String pharmacyName;
    private String pharmacyAddress;
    private String pharmacyPhoneNumber;

}

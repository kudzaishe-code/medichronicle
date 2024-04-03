package zw.co.danhiko.medichronicle.dto.Pharmacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PharmacyUpdateRequest {
    private String pharmacyName;
    private String phoneNumber;
}

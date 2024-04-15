package zw.co.danhiko.medichronicle.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class HospitalUpdateRequest {
    private String hospitalContact;
}

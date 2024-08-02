package zw.co.danhiko.medichronicle.dto.Pills;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class PillsDetailsDTO {
    private String name;
    private String dosage;
    private String frequency;

}
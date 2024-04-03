package zw.co.danhiko.medichronicle.service.medichronicle.impl.doctorImpl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DoctorRegistration {
    private String doctorNationalId;
    private String doctorName;
    private String doctorPhoneNumber;
    private String location;
}

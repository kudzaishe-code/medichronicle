package zw.co.danhiko.medichronicle.repository.MedicalRecords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords,Long> {

  Optional<MedicalRecords> findByPatientNationalIdIgnoreCase(String patientNationalId);
    boolean existsByPatientNationalIdIgnoreCase(String patientNationalId);
    List<MedicalRecords> findAllPatientsMedicalRecordsByDoctorNationalId(String doctorNationalId);
    List<MedicalRecords> findAllByDoctorNationalIdIgnoreCase(String doctorNationalId);
    List<MedicalRecords> findAllByPatientNationalIdIgnoreCase(String patientNationalId);

  Optional<MedicalRecords> findByPatientNationalIdIgnoreCaseAndDayAdmitted(String patientNationalId, LocalDate medicalRecordCreationDate);
}



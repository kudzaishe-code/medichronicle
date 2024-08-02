package zw.co.danhiko.medichronicle.repository.MedicalRecords;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords,Long> {

  List<MedicalRecords> findByPatientNationalIdIgnoreCase(String patientNationalId);
   Optional<MedicalRecords> findAllByPatientNationalIdIgnoreCase(String patientNationalId);


}



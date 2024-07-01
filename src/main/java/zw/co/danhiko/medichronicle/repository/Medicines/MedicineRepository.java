package zw.co.danhiko.medichronicle.repository.Medicines;

import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository {

    boolean existsByMedicineNameIgnoreCase(String medicineName);

}

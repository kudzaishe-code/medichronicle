package zw.co.danhiko.medichronicle.repository.Pills;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;

import java.util.Optional;

public interface PillRepository extends JpaRepository<PillsDetails, Long> {

    Optional<PillsDetails> findById(Long id);
    //save all the pills
  //  Optional<PillsDetails> saveAll(PillsDetails pillsDetails);
}

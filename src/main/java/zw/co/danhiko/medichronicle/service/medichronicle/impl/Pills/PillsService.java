package zw.co.danhiko.medichronicle.service.medichronicle.impl.Pills;

import zw.co.danhiko.medichronicle.models.Pills.PillsDetails;
import zw.co.danhiko.medichronicle.models.medicalRecords.MedicalRecords;

import java.util.List;

public interface PillsService {
    List<PillsDetails> parsePrescription(String prescription, MedicalRecords medicalRecords);
}

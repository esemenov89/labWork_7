package code.model.dao;

import code.model.dto.ElcatalogDTO;
import java.util.ArrayList;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitDAO {
    ArrayList<ElcatalogDTO> getAllStorageUnits();
    ElcatalogDTO getStorageUnitByISN(String isn);
    void delStorageUnitByISN(String isn);
    void addStorageUnit(ElcatalogDTO storageUnit);
}

package code.services;

import code.model.dto.ElcatalogDTO;
import code.model.hibernate.ElcatalogEntity;
import code.model.pojo.NewStorageUnit;
import code.model.pojo.StorageUnit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitService {
    ArrayList<ElcatalogDTO> getAllStorageUnits();
    ElcatalogDTO getStorageUnitByISN(String isn);
    void delStorageUnitByISN(String isn);
    ElcatalogDTO validateStorageUnit(NewStorageUnit newStorageUnit);
    void addStorageUnit(ElcatalogDTO storageUnit);
}

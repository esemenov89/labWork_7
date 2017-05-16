package code.model.dao;

import code.model.hibernate.ElcatalogEntity;
import code.model.pojo.StorageUnit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Created by admin on 22.04.2017.
 */
public interface StorageUnitDAO {
    ArrayList<ElcatalogEntity> getAllStorageUnits();
    ElcatalogEntity getStorageUnitByISN(String isn);
    void delStorageUnitByISN(String isn);
    void addStorageUnit(ElcatalogEntity storageUnit);
}

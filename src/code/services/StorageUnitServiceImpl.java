package code.services;

import code.model.dao.StorageUnitDAO;
import code.model.pojo.NewStorageUnit;
import code.model.pojo.StorageUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Service for working with storage units
 */
@Service
public class StorageUnitServiceImpl implements StorageUnitService {

    private StorageUnitDAO storageUnitDAO;

    @Autowired
    public void setStorageUnitDAO(StorageUnitDAO storageUnitDAO) {
        this.storageUnitDAO = storageUnitDAO;
    }

    /**
     *
     * @return all storage units from database
     */
    @Override
    public ArrayList<StorageUnit> getAllStorageUnits(){
        ArrayList<StorageUnit> storageUnits = null;
        storageUnits = storageUnitDAO.getAllStorageUnits();
        return storageUnits;
    }

    /**
     *
     * @param isn - identifier of storage unit
     * @return storage unit with isn identifier from database
     */
    @Override
    public StorageUnit getStorageUnitByISN(String isn) {
        StorageUnit storageUnit = null;
        storageUnit = storageUnitDAO.getStorageUnitByISN(isn);
        return storageUnit;
    }

    /**
     * Add storage unit in database
     * @param storageUnit
     */
    @Override
    public void addStorageUnit(StorageUnit storageUnit) {
        storageUnitDAO.addStorageUnit(storageUnit);
    }

    /**
     * delete storage unit with isn identifier from database
     * @param isn
     */
    @Override
    public void delStorageUnitByISN(String isn) {
        storageUnitDAO.delStorageUnitByISN(isn);
    }

    /**
     * Validate storage unit
     * Use it before added storage unit in database
     * @param newStorageUnit - new storage unit
     * @return validated storage unit or storage this errors
     */
    @Override
    public StorageUnit validateStorageUnit(NewStorageUnit newStorageUnit){

        StorageUnit storageUnit = new StorageUnit(newStorageUnit.getAuthor(),newStorageUnit.getTitle(),
                newStorageUnit.getPublishingHouse(),newStorageUnit.getCity(),1,1,
                newStorageUnit.getIsn(),newStorageUnit.getText());
        Pattern p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ0-9-\\s.,_]{1,50}$+");
        Matcher m = p.matcher(newStorageUnit.getAuthor());
        if (!m.matches()){
            storageUnit.setAuthor("@Error1");
        }
        p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ.,0-9-\\s_]{1,50}$+");
        m = p.matcher(newStorageUnit.getTitle());
        if (!m.matches()){
            storageUnit.setTitle("@Error1");
        }
        m = p.matcher(newStorageUnit.getPublishingHouse());
        if (!m.matches()){
            storageUnit.setPublishingHouse("@Error1");
        }
        m = p.matcher(newStorageUnit.getCity());
        if (!m.matches()){
            storageUnit.setCity("@Error1");
        }
        p = Pattern.compile("^[0-9]{1,4}$+");
        m = p.matcher(newStorageUnit.getYear());
        if (!m.matches()){
            storageUnit.setYear(-1);
        }
        else{
            storageUnit.setYear(Integer.parseInt(newStorageUnit.getYear()));
        }
        m = p.matcher(newStorageUnit.getPagesCount());
        if (!m.matches()){
            storageUnit.setPagesCount(-1);
        }
        else{
            storageUnit.setPagesCount(Integer.parseInt(newStorageUnit.getPagesCount()));
        }
        p = Pattern.compile("^[a-zA-Zа-яА-ЯёЁ0-9-]{1,50}$+");
        m = p.matcher(newStorageUnit.getIsn());
        if (!m.matches()){
            storageUnit.setIsn("@Error1");
        }
        if (newStorageUnit.getText().replaceAll(" ","").equals("") || newStorageUnit.getText().length()>1_000_000){
            storageUnit.setText("@Error1");
        }

        return storageUnit;
    }
}

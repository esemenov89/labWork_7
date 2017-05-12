package code.model.dao;

import code.services.ConnectionPool;
import code.model.pojo.StorageUnit;
import code.services.LibraryException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class for data exchange with database
 */
@Repository
public class StorageUnitDAOImpl implements StorageUnitDAO {

    private static final Logger LOGGER = Logger.getLogger(StorageUnitDAOImpl.class);

    /**
     *
     * @return all storage units from database
     */
    public ArrayList<StorageUnit> getAllStorageUnits(){
        StorageUnit storageUnit=null;
        ArrayList<StorageUnit> storageUnits = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "SELECT * FROM ELCATALOG")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                storageUnit = createEntity(resultSet);
                storageUnits.add(storageUnit);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new LibraryException("SQLException","getAllStorageUnits");
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return storageUnits;
    }

    /**
     * Add storage unit in database
     * @param storageUnit
     */
    @Override
    public void addStorageUnit(StorageUnit storageUnit){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement( "INSERT INTO ELCATALOG(AUTHOR,TITLE,PUBLISHING_HOUSE,CITY,YEAR," +
                             "PAGES_COUNT,ISN,TEXT) VALUES(?,?,?,?,?,?,?,?)")) {
            statement.setString(1,storageUnit.getAuthor());
            statement.setString(2,storageUnit.getTitle());
            statement.setString(3,storageUnit.getPublishingHouse());
            statement.setString(4,storageUnit.getCity());
            statement.setInt(5,storageUnit.getYear());
            statement.setInt(6,storageUnit.getPagesCount());
            statement.setString(7,storageUnit.getIsn());
            statement.setString(8,storageUnit.getText());
            statement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new LibraryException("SQLException","addStorageUnit");
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }

    /**
     *
     * @param isn - identifier of storage unit
     * @return storage unit with isn identifier from database
     */
    public StorageUnit getStorageUnitByISN(String isn){
        StorageUnit storageUnit=null;

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement( "SELECT * FROM ELCATALOG WHERE ISN=?")) {

            preparedStatement.setString(1, isn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                storageUnit = createEntity(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new LibraryException("SQLException","getStorageUnitByISN");
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return storageUnit;
    }

    /**
     * delete storage unit with isn identifier from database
     * @param isn
     */
    @Override
    public void delStorageUnitByISN(String isn){

        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement( "DELETE FROM READ_STORAGE_UNIT WHERE STORAGE_UNIT=?");
            preparedStatement.setString(1, isn);
            preparedStatement.execute();

            preparedStatement=connection.prepareStatement("DELETE FROM ELCATALOG WHERE ISN=?");
            preparedStatement.setString(1, isn);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new LibraryException("SQLException","delStorageUnitByISN");
        }
        catch (Exception e){
            LOGGER.error(e);
        }
    }

    /**
     * Create storage unit from data of database
     * @param resultSet
     * @return
     */
    private StorageUnit createEntity(ResultSet resultSet) {
        StorageUnit storageUnit=null;
        try {
            storageUnit = new StorageUnit(resultSet.getString("AUTHOR"),
                    resultSet.getString("TITLE"),
                    resultSet.getString("PUBLISHING_HOUSE"),
                    resultSet.getString("CITY"),
                    resultSet.getInt("YEAR"),
                    resultSet.getInt("PAGES_COUNT"),
                    resultSet.getString("ISN"),
                    resultSet.getString("TEXT"));
        }
        catch (Exception e){
            LOGGER.error(e);
        }
        return storageUnit;
    }
}

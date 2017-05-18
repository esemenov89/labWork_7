package code.model.dao;

import code.model.dto.ElcatalogDTO;
import code.model.hibernate.ElcatalogEntity;
import code.model.hibernate.HibernateSessionFactory;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Class for data exchange with database
 */
@Repository
public class StorageUnitDAOImpl implements StorageUnitDAO {
    private static final Logger LOGGER = Logger.getLogger(StorageUnitDAOImpl.class);
    MapperFacade mapperBooks;

    @Autowired
    public void setMapperFacade(MapperFacade mapperFacade) {
        this.mapperBooks = mapperFacade;
    }

    /**
     *
     * @return all storage units from database
     */
    public ArrayList<ElcatalogDTO> getAllStorageUnits(){
        ArrayList<ElcatalogDTO> storageUnitsDTO;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        storageUnitsDTO=(ArrayList)(mapperBooks.mapAsList(session.createCriteria(ElcatalogEntity.class).list(),ElcatalogDTO.class));
        session.close();
        return storageUnitsDTO;
    }

    /**
     * Add storage unit in database
     * @param storageUnit
     */
    @Override
    public void addStorageUnit(ElcatalogDTO storageUnit){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ElcatalogEntity book = mapperBooks.map(storageUnit,ElcatalogEntity.class);
        session.save(book);
        session.getTransaction().commit();
        session.close();
    }

    /**
     *
     * @param isn - identifier of storage unit
     * @return storage unit with isn identifier from database
     */
    public ElcatalogDTO getStorageUnitByISN(String isn){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ElcatalogDTO book = mapperBooks.map((ElcatalogEntity) session.get(ElcatalogEntity.class, isn),ElcatalogDTO.class);
        transaction.commit();
        session.close();
        return book;
    }

    /**
     * delete storage unit with isn identifier from database
     * @param isn
     */
    @Override
    public void delStorageUnitByISN(String isn){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        ElcatalogEntity elcatalogEntity = (ElcatalogEntity) session.get(ElcatalogEntity.class, isn);
        session.delete(elcatalogEntity);
        transaction.commit();
        session.close();
    }
}

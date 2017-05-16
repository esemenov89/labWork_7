package code.model.dao;

import code.model.hibernate.ElcatalogEntity;
import code.model.hibernate.HibernateSessionFactory;
import code.model.hibernate.UsersEntity;
import code.services.ConnectionPool;
import code.model.pojo.StorageUnit;
import code.services.LibraryException;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public ArrayList<ElcatalogEntity> getAllStorageUnits(){
        ArrayList<ElcatalogEntity> books = new ArrayList<>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<ElcatalogEntity> list = session.createCriteria(ElcatalogEntity.class).list();

        books=(ArrayList)list;

        session.close();
        return books;
    }

    /**
     * Add storage unit in database
     * @param storageUnit
     */
    @Override
    public void addStorageUnit(ElcatalogEntity storageUnit){

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ElcatalogEntity bookNew = new ElcatalogEntity(storageUnit.getAuthor(),storageUnit.getTitle(),
                storageUnit.getPublishingHouse(),storageUnit.getCity(),storageUnit.getYear(),
                storageUnit.getPagesCount(),storageUnit.getIsn(),storageUnit.getText());
        session.save(bookNew);
        session.getTransaction().commit();

        session.close();
    }

    /**
     *
     * @param isn - identifier of storage unit
     * @return storage unit with isn identifier from database
     */
    public ElcatalogEntity getStorageUnitByISN(String isn){
        ElcatalogEntity book = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from ElcatalogEntity where lower(isn) = :paramIsn");
        query.setParameter("paramIsn", isn.toLowerCase());
        List list = query.list();

        if(list.size()>0)
            book = (ElcatalogEntity)list.get(0);

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

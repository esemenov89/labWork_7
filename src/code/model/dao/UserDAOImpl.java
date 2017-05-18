package code.model.dao;

import code.model.dto.UserDTO;
import code.model.hibernate.HibernateSessionFactory;
import code.model.hibernate.UsersEntity;
import ma.glasnost.orika.MapperFacade;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Class for data exchange with database
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    MapperFacade mapperUsers;

    @Autowired
    public void setMapperFacade(MapperFacade mapperFacade) {
        this.mapperUsers = mapperFacade;
    }

    /**
     * Find user with login=login in database
     *
     * @param login
     * @return
     */
    @Override
    public UsersEntity findUserByLogin(String login) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UsersEntity user = (UsersEntity) session.get(UsersEntity.class, login);
        transaction.commit();
        session.close();

        return user;
    }

    /**
     * Method for lock or unlock user
     *
     * @param login - of user
     * @param lock  - if 1, then user will be locked, if 0, then user will be unlocked
     */
    @Override
    public void lockOrUnlockUser(String login, Long lock) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        UsersEntity user = (UsersEntity) session.get(UsersEntity.class, login);
        user.setEnabled(lock);
        session.update(user);
        transaction.commit();
        session.close();
    }

    /**
     * Find user with mail=mail in database
     *
     * @param mail
     * @return
     */
    @Override
    public UserDTO findUserByMail(String mail) {
        UserDTO user = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from UsersEntity where LOWER(mail)  = :paramMail");
        query.setParameter("paramMail", mail.toLowerCase());
        List list = query.list();
        if(list.size()>0)
            user = mapperUsers.map((UsersEntity)list.get(0),UserDTO.class);
        session.close();
        return user;
    }

    /**
     * Add user in database
     *
     * @param user
     */
    @Override
    public void addUser(UsersEntity user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Get all user from database
     *
     * @return
     */
    public ArrayList<UserDTO> getAllUsers() {
        ArrayList<UserDTO> usersDTO;
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        usersDTO=(ArrayList)(mapperUsers.mapAsList(session.createCriteria(UsersEntity.class).list(),UserDTO.class));
        session.close();
        return usersDTO;
    }
}

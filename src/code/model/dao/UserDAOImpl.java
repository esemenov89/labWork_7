package code.model.dao;


import code.model.hibernate.HibernateSessionFactory;
import code.model.hibernate.UsersEntity;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Class for data exchange with database
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    /**
     * Find user with login=login in database
     *
     * @param login
     * @return
     */
    @Override
    public UsersEntity findUserByLogin(String login) {
        UsersEntity user = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from UsersEntity where lower(nick) = :paramName");
        query.setParameter("paramName", login.toLowerCase());
        List list = query.list();

        if(list.size()>0)
            user = (UsersEntity)list.get(0);

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
        String hql = "UPDATE UsersEntity set enabled=:lock WHERE nick = :login";
        Query query = session.createQuery(hql);
        query.setParameter("lock", lock);
        query.setParameter("login", login);
        query.executeUpdate();
        session.close();
    }

    /**
     * Find user with mail=mail in database
     *
     * @param mail
     * @return
     */
    @Override
    public UsersEntity findUserByMail(String mail) {
        UsersEntity user = null;

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("from UsersEntity where LOWER(mail)  = :paramMail");
        query.setParameter("paramMail", mail.toLowerCase());
        List list = query.list();
        if(list.size()>0)
            user = (UsersEntity)list.get(0);

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
        UsersEntity userNew = new UsersEntity();
        userNew.setNick(user.getNick());
        userNew.setMail(user.getMail());
        userNew.setPassword(user.getPassword());
        userNew.setRole(user.getRole());
        userNew.setEnabled(user.getEnabled());

        session.save(userNew);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * Get all user from database
     *
     * @return
     */
    public ArrayList<UsersEntity> getAllUsers() {
        ArrayList<UsersEntity> users = new ArrayList<>();

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<UsersEntity> list = session.createCriteria(UsersEntity.class).list();

        users=(ArrayList)list;

        session.close();
        return users;
    }
}

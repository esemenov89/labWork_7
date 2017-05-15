package code.services;

import code.model.dao.UserDAOImpl;
import code.model.pojo.StorageUnit;
import code.model.pojo.User;
import org.apache.log4j.Logger;

import code.model.dao.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for working with users
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findUserByLogin(String login){
        return userDAO.findUserByLogin(login);
    }

    /**
     * Method for authorization user
     * @param login - of user
     * @param password - of user
     * @return
     */
    @Override
    public User auth(String login, String password){
        User user = null;
        user = userDAO.findUserByLogin(login);
        LOGGER.debug("user: " + user);
        if (user == null || !(BCrypt.checkpw(password, user.getPassword()))) {
            return null;
        }
        LOGGER.debug("user not blocked");
        return user;
    }

    /**
     * Validate user
     * Use it before added user in database
     * @param login - of new user
     * @param password - of new user
     * @param mail - of new user
     * @return validated user or user with errors
     */
    @Override
    public User validateUser(String login, String password, String mail){
        User user = new User(login, mail, password, "ROLE_USER", 1);
        Pattern p = Pattern.compile("^[a-zA-Z0-9]{2,16}$+");
        Matcher m = p.matcher(login);
        if (!m.matches()) {
            user.setLogin("@Error1");
        }
        if (userDAO.findUserByLogin(login) != null) {
            user.setLogin("@Error2");
        }
        p = Pattern.compile("^[a-zA-Z0-9]{8,16}$+");
        m = p.matcher(password);
        if (!m.matches()) {
            user.setPassword("@Error1");
        }
        p = Pattern.compile("^[a-zA-Z0-9.-@]{8,30}$+");
        m = p.matcher(mail);
        if (!m.matches()) {
            user.setMail("@Error1");
        }
        if (userDAO.findUserByMail(mail) != null) {
            user.setMail("@Error2");
        }
        return user;
    }

    /**
     * Add user in database
     * @param user
     */
    @Override
    public void addUser(User user){
        userDAO.addUser(user);
    }

    /**
     * Method for lock or unlock user
     * @param nick - of user
     * @param lock - if 1, then user will be locked, if 0, then user will be unlocked
     */
    @Override
    public void lockOrUnlockUser(String nick,int lock){
        userDAO.lockOrUnlockUser(nick, lock);
    }

    /**
     * Get all user from database
     * @return
     */
    @Override
    public ArrayList<User> getAllUsers(){
        ArrayList<User> users = null;
        users = userDAO.getAllUsers();
        return users;
    }
}

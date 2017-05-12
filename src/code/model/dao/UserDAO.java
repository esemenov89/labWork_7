package code.model.dao;

import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserDAO {
    User findUserByLoginAndPassword(String login, String password);
    User findUserByLogin(String login);
    User findUserByMail(String mail);
    ArrayList<User> getAllUsers();
    void addUser(User user);
    void lockOrUnlockUser(String nick,int lock);
}

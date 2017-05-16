package code.model.dao;

import code.model.hibernate.UsersEntity;
import code.model.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public interface UserDAO {
    UsersEntity findUserByLogin(String login);
    UsersEntity findUserByMail(String mail);
    ArrayList<UsersEntity> getAllUsers();
    void addUser(UsersEntity user);
    void lockOrUnlockUser(String nick,Long lock);
}

package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.User;

public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {


        this.userDAO = userDAO;
    }


    public void save(User user){
        if(userDAO.existByEmail(user.getEmail())) {

            throw new RuntimeException("User exists with the email " + user.getEmail());
        }
        else{
            userDAO.save(user);
        }
    }
}

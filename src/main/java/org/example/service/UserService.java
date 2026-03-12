package org.example.service;

import org.example.dao.UserDAO;
import org.example.models.User;

import java.util.Optional;

public class UserService {

    private UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAO();
    }


    public void save(User user){
        if(userDAO.existByEmail(user.getEmail())) {

            throw new RuntimeException("User exists with the email " + user.getEmail());
        }
        else{
            userDAO.save(user);
        }
    }

    public User findByEmail(String email) {

        Optional<User> userOptional = userDAO.findByEmail(email);

        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new RuntimeException("User with email " + email + " not found");

    }



    public void deleteByEmail(String email) {


        userDAO.deleteByEmail(email);
    }


    public void deleteById(Long id) {

        userDAO.deleteById(id);
    }
}


package org.example.service;

import org.example.models.User;
import org.example.smtp_config.SendEmail;

import java.util.Optional;

public class UserService {

    private org.example.dao.UserDAO userDAO;
    private SendEmail sendEmail;

    public UserService() {
        this.userDAO = new org.example.dao.UserDAO();
        this.sendEmail = new SendEmail();
    }


    public void save(User user){
        if(userDAO.existByEmail(user.getEmail())) {

            throw new RuntimeException("User exists with the email " + user.getEmail());
        }
        else{
            userDAO.save(user);
            sendEmail.sendEmail(user.getEmail(), "Registration", "Welcome " + user.getName());
        }
    }

    public void update(User user){
        userDAO.update(user);
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


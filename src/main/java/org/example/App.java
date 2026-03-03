package org.example;

import org.example.config.HibernateUtil;
import org.example.dao.UserDAO;
import org.example.models.Task;
import org.example.models.User;
import org.example.supportEnum.Category;
import org.example.supportEnum.Status;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();


        List<Task> tasks = new ArrayList<>();

        User user = new User("Nikita", "454tttt@ukr.net",1256,true, tasks);

        Collections.addAll(tasks, new Task("Task1", "Blalalal",
                LocalDate.of(2026, Month.MARCH, 1 ), user, Category.HOME, Status.NEW ));


        UserDAO userDAO = new UserDAO();
        userDAO.save(user);

    }
}

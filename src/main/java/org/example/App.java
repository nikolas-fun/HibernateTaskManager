package org.example;

import org.example.smtp_config.SendEmail;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
   /*     SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();


        List<Task> tasks = new ArrayList<>();

        User user = new User("Nikita", "454tttt@ukr.net",1256,true, tasks);

        Collections.addAll(tasks, new Task("Task1", "Blalalal",
                LocalDate.of(2026, Month.MARCH, 1 ), user, Category.HOME, Status.NEW ));


        UserDAO userDAO = new UserDAO();

        UserService userService = new UserService(userDAO);


        userService.save(user);*/


        SendEmail emailSender = new SendEmail();

        emailSender.sendEmail("007rydik007@gmail.com", "Test Subject", "Лучше б я на стройку пошёл");

    }
}

package org.example;

import org.example.dao.CommentDAO;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.models.User;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.example.supportEnum.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

   /*     SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();


        List<Task> tasks = new ArrayList<>();

        User user = new User("Nikita", "454tttt@ukr.net",1256,true, tasks);

        Collections.addAll(tasks, new Task("Task1", "Blalalal",
                LocalDate.of(2026, Month.MARCH, 1 ), user, Category.HOME, Status.NEW ));


        UserDAO userDAO = new UserDAO();

        UserService userService = new UserService(userDAO);


        userService.save(user);*/


        /*SendEmail emailSender = new SendEmail();

        emailSender.sendEmail("007rydik007@gmail.com", "Test Subject", "Лучше б я на стройку пошёл");*/

        List<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        org.example.dao.UserDAO userDAO = new org.example.dao.UserDAO();
        org.example.dao.TaskDAO taskDAO = new org.example.dao.TaskDAO();
        TaskService taskService = new TaskService(taskDAO);
        UserService userService = new UserService(userDAO);

        boolean startMenu = true;

        while (startMenu) {
            System.out.println("\n=== CONTROL SYSTEM ===");
            System.out.println("1. Add User");
            System.out.println("2. Add Task");
            System.out.println("3. Add Comment");
            System.out.println("4. Show all Tasks");
            System.out.println("5. Update Task");
            System.out.println("6. Update User");
            System.out.println("7. Delete Task");
            System.out.println("8. Find Category");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();


            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("Please enter the user NAME: ");
                    String name = sc.next();
                    System.out.println("Please enter the EMAIL: ");
                    String email = sc.next();
                    System.out.println("Please enter the PASSWORD: ");
                    int password = sc.nextInt();
                    System.out.println("Please enter the ACTIVE(true/false): ");
                    boolean active = sc.nextBoolean();


                    User user = new User(name, email, password, active, tasks);

                    userService.save(user);
                    System.out.println("User has been added");
                }
                case 2 -> {
                    System.out.println("Please enter the task NAME: ");
                    String name = sc.next();
                    System.out.println("Please enter the description: ");
                    String description = sc.next();
                    System.out.println("Please enter the DATE: ");
                    int localDate = sc.nextInt();

                    System.out.println("Please enter the Category: ");
                    String category = sc.next();
                    Task task = new Task(name, description, localDate, user ,category, Status.NEW );


                    taskService.save2(task);
                    System.out.println("Task has been added");
                }
                case 3 -> {
                    /*System.out.println("Please enter the TASK: ");
                    String task = sc.next();*/
                    System.out.println("Please enter the comment: ");
                    String text = sc.next();

                    Comment comment = new Comment(text);
                    CommentDAO.save();
                    System.out.println("Comment has been added");

                }
                case 4 -> {
                        for (Task task: tasks) {
                            System.out.println("ID-->" + " " + task.getId() + "\n" + "Name-->" + " " + task.getTitle()
                                    + "\n" + "Category-->" + task.getCategory());

                        }
                }

            }
        }
    }
}


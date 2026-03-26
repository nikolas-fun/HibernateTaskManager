package org.example;

import org.example.dao.CommentDAO;
import org.example.dao.TaskDAO;
import org.example.dao.UserDAO;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.models.User;
import org.example.service.CommentService;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.example.supportEnum.Category;
import org.example.supportEnum.Status;
import org.example.use_case.CompleteTaskWithCommentUseCase;
import org.example.use_case.TransferTasksBetweenUsersUseCase;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

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

        Scanner sc = new Scanner(System.in);
        TaskService taskService = new TaskService();
        UserService userService = new UserService();
        CommentService commentService = new CommentService();
        CompleteTaskWithCommentUseCase completeTaskWithCommentUseCase = new CompleteTaskWithCommentUseCase();
        TransferTasksBetweenUsersUseCase transferTasksBetweenUsersUseCase = new TransferTasksBetweenUsersUseCase();

        boolean startMenu = true;

        while (startMenu) {
            System.out.println("\n=== CONTROL SYSTEM ===");
            System.out.println("1. Add User");
            System.out.println("2. Add Task");
            System.out.println("3. Add Comment");
            System.out.println("4. Show all Tasks");
            System.out.println("5. Update Task");
            System.out.println("6. Update User");
            System.out.println("7. Find Count Task By UserId");
            System.out.println("8. Find Category");
            System.out.println("9. Transfer Task Between Users");
            System.out.println("10. Complete Task With Comment ");
            System.out.println("11. Delete Task");
            System.out.println("12. Delete User by Email");
            System.out.println("13. Delete User by Id");
            System.out.println("14. Exist User Email");
            System.out.println("15. Find User by Email");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.hasNextLine();



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


                    User user = new User(name, email, password, active);

                    userService.save(user);
                    System.out.println("User has been added");
                }
                case 2 -> {
                    System.out.println("Please enter the USER ID: ");
                    Long userId = sc.nextLong();
                    sc.nextLine();
                    User user = userService.findById(userId);

                    System.out.println("Please enter the task NAME: ");
                    String name = sc.nextLine();
                    System.out.println("Please enter the DESCRIPTION: ");
                    String description = sc.nextLine();
                    System.out.println("Please enter the DATE: ");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                    String inputDate = sc.nextLine();
                    LocalDate dueDay = LocalDate.parse(inputDate, formatter);

                    System.out.println("Please enter the Category   " +
                            "    WORK,\n" +
                            "    STUDY,\n" +
                            "    HOME,\n" +
                            "    RELAX,\n" +
                            "    HOBBY;: ");

                    String inputCategory = sc.nextLine().toUpperCase();
                    Category category = Category.valueOf(inputCategory);

                    Task task = new Task(name, description, dueDay, user, category , Status.NEW);


                    taskService.save2(task);
                    System.out.println("Task has been added");
                }
                case 3 -> {
                    System.out.println("Please enter the USER ID: ");
                    Long userId = sc.nextLong();
                    sc.nextLine();
                    User user = userService.findById(userId);

                    System.out.println("Please enter the TASK ID: ");
                    Long taskId = sc.nextLong();
                    sc.nextLine();
                    Task task = taskService.findById(taskId);

                    System.out.println("Please enter the comment: ");
                    String text = sc.nextLine();

                    Comment comment = new Comment(text, user,task);
                    commentService.save(comment);
                    System.out.println("Comment has been added");

                }
                case 4 -> {
                  taskService.findAll().forEach(System.out::println);
                  ;

                }
                case 5 -> {
                    System.out.println("Please enter TASK ID: ");
                    Long taskId = sc.nextLong();
                    sc.nextLine();
                    Task task = taskService.findById(taskId);

                    System.out.println("Please enter User ID: ");
                    Long userId = sc.nextLong();
                    sc.nextLine();
                    User user = userService.findById(userId);

                    System.out.println("Please enter new NAME: ");
                    String title = sc.nextLine();
                    sc.nextLine();
                    if(title !=null &&   !title.isBlank()){
                       task.setTitle(title);
                    }
                    System.out.println("Please enter new DESCRIPTION: ");
                    String description = sc.nextLine();
                    sc.nextLine();
                    if(description !=null &&   !description.isBlank()){
                        task.setDescription(description);
                    }
                    System.out.println("Please enter the DATE: ");
                    String inputDate = sc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    sc.nextLine();

                    LocalDate dueDay = LocalDate.parse(inputDate,formatter);
                    if(inputDate !=null &&   !inputDate.isBlank()){
                        task.setDueDate(dueDay);
                    }
                    System.out.println("Please enter the Category   " +
                            "    WORK,\n" +
                            "    STUDY,\n" +
                            "    HOME,\n" +
                            "    RELAX,\n" +
                            "    HOBBY;: ");

                    String inputCategory = sc.nextLine().toUpperCase();
                    sc.nextLine();
                    Category category = Category.valueOf(inputCategory);
                    if(category !=null){
                        task.setCategory(category);
                    }
                    System.out.println("Please enter the Status     " +
                            "    NEW,\n" +
                            "    IN_PROGRESS,\n" +
                            "    DONE,\n" +
                            "    CANCELLED;");

                    String inputStatus = sc.nextLine().toUpperCase();
                    sc.nextLine();
                    Status status = Status.valueOf(inputStatus);
                    if(inputStatus !=null){
                        task.setStatus(status);
                    }

                    task.setUser(user);
                    taskService.update(task);

                    System.out.println("Task updated");
                }
                case 6 -> {
                    System.out.println("Please enter USER ID: ");
                    Long userId = sc.nextLong();
                    User user = userService.findById(userId);
                    System.out.println("Please enter new NAME: ");

                    String name = sc.nextLine();
                    if(name !=null && !name.isBlank()){
                        user.setName(name);
                    }
                    sc.nextLine();
                    System.out.println("Please enter new EMAIL: ");
                    String email = sc.nextLine();
                    System.out.println("Please enter new PASSWORD: ");
                    int password = sc.nextInt();

                    System.out.println("Please enter the ACTIVE(true/false): ");
                    boolean active = sc.nextBoolean();

                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setActive(active);

                    userService.update(user);
                    System.out.println("User updated.");
                }
                case 7 -> {
                    System.out.println("Please enter USER ID: ");
                    Long userId = sc.nextLong();
                         Long count = taskService.findCountTaskByUserId(userId);

                    System.out.println("Task count = " + count);

                }
                case 8 -> {
                    System.out.println("Please enter CATEGORY:   (WORK,\n" +
                            "    STUDY,\n" +
                            "    HOME,\n" +
                            "    RELAX,\n" +
                            "    HOBBY;) ");
                    String category = sc.next();

                    List<Task> tasksByCategory = taskService.findByCategory(Category.valueOf(category));

                    for (Task task : tasksByCategory) {
                        System.out.println("ID --> " + task.getId());
                        System.out.println("Name --> " + task.getTitle());
                        System.out.println("Category --> " + task.getCategory());
                        System.out.println("----------------------");
                    }
                }
                case 9 -> {
                    System.out.println("Please write User ID");
                    Long fromUserTaskId = sc.nextLong();

                    System.out.println("Please write Task ID");
                    Long targetTaskId = sc.nextLong();

                    System.out.println("Please enter the Person ID to whom the task should be assigned");
                    Long toUserId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Please enter the comment: ");
                    String text = sc.nextLine();

                    transferTasksBetweenUsersUseCase.execute(fromUserTaskId, toUserId,targetTaskId, text);

                    System.out.println("Task sent");

                }
                case 10 -> {// проверка
                    System.out.println("Please write User ID:");
                    Long userId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Please write Task ID:");
                    Long taskId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Please enter the comment: ");
                    String text = sc.nextLine();
                    completeTaskWithCommentUseCase.execute(userId,taskId, text);

                    System.out.println("Task completed");
                }
                case 11 -> {

                    System.out.print("Enter task id to delete: ");
                    Long taskId = sc.nextLong();

                    taskService.deleteById(taskId);

                    System.out.println("Task deleted");

                }
                case 12 -> {
                    System.out.println("Please enter User Email: ");
                    String email = sc.nextLine();

                    userService.deleteByEmail(email);
                    System.out.println("User(email) deleted");
                }
                case 13 -> {
                    System.out.println("Please enter User ID: ");
                    Long id = sc.nextLong();

                    userService.deleteById(id);
                    System.out.println("User(id) deleted");
                }
                case 14 -> {
                    sc.nextLine();
                    System.out.println("Please enter User Email: ");
                    String email = sc.nextLine();

                    System.out.println(userService.existByEmail(email));

                }
                case 15 -> {
                    sc.nextLine();
                    System.out.println("Please enter User Email: ");
                    String email = sc.nextLine();

                    System.out.println((userService.findByEmail(email)));

                }

            }

    }
        }
}


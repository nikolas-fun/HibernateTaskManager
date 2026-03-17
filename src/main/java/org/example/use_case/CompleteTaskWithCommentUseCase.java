package org.example.use_case;

import org.example.config.HibernateUtil;
import org.example.dao.TaskDAO;
import org.example.dao.UserDAO;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.models.User;
import org.example.service.CommentDAO;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.example.supportEnum.Status;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.models.spi.UserTypeRegistration;

public class CompleteTaskWithCommentUseCase {

    private TaskDAO taskDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    public CompleteTaskWithCommentUseCase() {
        this.commentDAO = new CommentDAO();
        this.userDAO = new UserDAO();
        this.taskDAO = new TaskDAO();
    }

    public void execute(Long userId, String text) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User user = userDAO.findById(userId).orElseThrow();
            Task task = taskDAO.findByUserId(userId).orElseThrow();

            task.setStatus(Status.DONE);

            Comment comment = new Comment();
            comment.setText(text);
            comment.setTask(task);
            comment.setUser(user);

            commentDAO.save(comment);

            transaction.commit();

        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}

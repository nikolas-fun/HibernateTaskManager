package org.example.use_case;

import org.example.config.HibernateUtil;
import org.example.dao.CommentDAO;
import org.example.dao.TaskDAO;
import org.example.dao.UserDAO;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TransferTasksBetweenUsersUseCase {
    private TaskDAO taskDAO;
    private UserDAO userDAO;
    private CommentDAO commentDAO;

    public TransferTasksBetweenUsersUseCase() {
        this.commentDAO = new CommentDAO();
        this.userDAO = new UserDAO();
        this.taskDAO = new TaskDAO();
    }

    public void execute(Long fromUserId, Long toUserId, Long targetTaskId, String text) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User fromUser = userDAO.findById(fromUserId).orElseThrow();
            User toUser = userDAO.findById(toUserId).orElseThrow();

            if (fromUser == null || toUser == null) {
                throw new RuntimeException("User not found");
            }

            Task task = taskDAO.findById(targetTaskId).orElseThrow();

            if(!task.getUser().getId().equals(fromUser.getId()) ){
                throw new RuntimeException("Wrong owner");
            }

            task.setUser(toUser);
            session.merge(task);

            Comment comment = new Comment();
            comment.setText(text);
            comment.setTask(task);
            comment.setUser(toUser);

            commentDAO.save(comment);

            transaction.commit();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}

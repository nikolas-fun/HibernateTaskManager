package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class CommentDAO {
    /*Базовый набор:

    -- Поиск по id
   	-- Сохранение в бд
   	-- Удаление по id

    Теперь отдельно основные методы для связей

   	-- Найти все комментарии по id задачи
   	-- Найти все комментарии по id пользователя
   	-- Найти все комментарии по id задачи отсортированные по дате создания (поле createdAt)*/


    public Optional<Comment> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return Optional.ofNullable(session.find(Comment.class, id));// select Comment from user where id = ?

        } catch (Exception e) {
            throw new NullPointerException("Comment not found");
        }
    }
    public void save(Comment comment) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(comment);// insert into comment values
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Comment commentFromDB = session.find(Comment.class, id);   //DELETE FROM Comment WHERE id = ?

            if (commentFromDB == null) {
                throw new NullPointerException("Comment not found");
            }

            session.remove(commentFromDB);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Comment> findAllCommentByIdTask(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(" from Comment  c where c.task.id =:id ", Comment.class)
                    .setParameter("id", id)
                    .list();

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }
    public List<Comment> findAllCommentByIdUser(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(" from Comment  c where c.user.id =:id ", Comment.class)
                    .setParameter("id", id)
                    .list();
        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }
    public List<Comment> findAllCommentByIdTaskOrderByCreated(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(" from Comment  c where c.task.id =:id  order by c.createdAt asc", Comment.class)
                    .setParameter("id", id)
                    .list();
        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }
}

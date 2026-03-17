package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.models.Task;
import org.example.models.User;
import org.example.supportEnum.Category;
import org.example.supportEnum.Status;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class TaskDAO {

   /* -- Сохранение в бд
	-- Поиск по id
	-- вернуть количество задач пользователя по userId******
	-- Поиск по категориям
	-- Поиск по статусу
	-- Удаление по id*/

   public void save(Task task) {

       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
           Transaction transaction = session.beginTransaction();
           session.persist(task);// insert into task values
           transaction.commit();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public Optional<Task> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return Optional.ofNullable(session.find(Task.class, id));// select user from task where id = ?

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }
    public Optional<Long> findCountTaskByUserId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return  ( Optional<Long>) session.createQuery("select Count(u) from Task u where u.user.id =:id ")
                    .setParameter("id", id)
                    .uniqueResultOptional();

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }

    public Optional<Task> findByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return   session.createQuery("select u from Task u where u.user.id =:userId ")
                    .setParameter("userId", userId)
                    .uniqueResultOptional();

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }


    public List<Task> findByCategory(Category category) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("from Task t where t.category =:category")
                    .setParameter("category", category)
                    .getResultList();

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }

    public List<Task> findByStatus(Status status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("from Task t where t.status =:status")
                    .setParameter("status", status)
                    .getResultList();

        } catch (Exception e) {
            throw new NullPointerException("Task not found");
        }
    }
    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Task taskFromDB = session.find(Task.class, id);   //DELETE FROM Task WHERE id = ?

            session.remove(taskFromDB);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean existById(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("select 1 from Task u where u.id =:id")// ?
                    .setParameter("id", id)
                    .setMaxResults(1)
                    .uniqueResultOptional()
                    .isPresent();


        } catch (Exception e) {
            throw new NullPointerException("Task not found");

        }
    }

    public void update(Task task){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.merge(task);

            transaction.commit();

        }
        catch (Exception e) {

            throw new RuntimeException("Task does not update");
        }
    }
    }

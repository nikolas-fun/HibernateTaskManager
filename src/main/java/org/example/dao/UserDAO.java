package org.example.dao;

import org.example.config.HibernateUtil;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserDAO {


    /*-- Регистрация
	-- Поиск по id
	-- Поиск по email
	-- Удаление по email
	-- Удаление по id
	-- Проверка есть ли такой пользователь с определенным email*/

    public void save(User user) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            session.persist(user);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) transaction.rollback();

            throw new RuntimeException("User not found");
        }
    }


    public Optional<User> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return Optional.ofNullable(session.find(User.class, id));// select user from user where id = ?

        } catch (Exception e) {
            throw new NullPointerException("User not found");
        }
    }

    public Optional<User> findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User u where u.email =:email")// ?
                    .setParameter("email", email)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new NullPointerException("User not found");

        }
    }

    public void deleteByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User userFromDB = (User) session.createQuery("from User u where u.email=:email ")
                    .setParameter("email", email)
                    .uniqueResult();

            if (userFromDB == null) {
                throw new NullPointerException("User not found");
            }

            session.remove(userFromDB);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            User userFromDB = session.find(User.class, id);   //DELETE FROM User WHERE id = ?

            if (userFromDB == null) {
                throw new NullPointerException("User not found");
            }

            session.remove(userFromDB);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existByEmail(String email) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("select 1 from User u where u.email =:email")// ?
                    .setParameter("email", email)
                    .setMaxResults(1)
                    .uniqueResultOptional()
                    .isPresent();


        } catch (Exception e) {
            throw new NullPointerException("User not found");

        }
    }


}

package org.example;

import org.example.config.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SessionFactory sessionFactory  = HibernateUtil.getSessionFactory();
    }
}

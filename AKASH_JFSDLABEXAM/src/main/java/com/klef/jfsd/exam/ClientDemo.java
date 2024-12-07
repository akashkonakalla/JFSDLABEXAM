package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
            int updatedCount = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .executeUpdate();

            System.out.println("Number of records updated: " + updatedCount);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}

package org.example.hibernate.dao;

import org.example.hibernate.model.Student;
import org.example.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class StudentDao {

    public void saveStudent(Student student) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void getStudent(long id) {
        Transaction transaction = null;
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = " FROM Student S WHERE S.id = :studentId";
            Query query = session.createQuery(hql);
            query.setParameter("studentId", id);
            List results = query.getResultList();
            if (results != null && results.isEmpty()) {
                student = (Student) results.get(0);
            }
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }


    public void insertStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "INSERT INTO Student (firstName, lastName, email)" +
                    "SELECT firstName, lastName, email FROM Student";
            Query query = session.createQuery(hql);

            int result = query.executeUpdate();
            System.out.println("Rows affected: " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "UPDATE Student SET firstName = :firstName " + "WHERE id = :studentId";
            Query query = session.createQuery(hql);
            int result = query.executeUpdate();
            System.out.println("Rows Affected " + result);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void deleteStudent(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Student student = session.get(Student.class, id);
            if (student != null) {
                String hql = "DELETE FROM Student " + "WHERE id = :studentId";
                Query query = session.createQuery(hql);
                query.setParameter("studentId", id);
                int result = query.executeUpdate();
                System.out.println("Rows affected : " + result);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

package com.prachi.scholarshipfinder.daoimpl;

import com.prachi.scholarshipfinder.dao.ScholarshipDAO;
import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScholarshipDAOImpl implements ScholarshipDAO {

    @Override
    public void addScholarship(Scholarship scholarship) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(scholarship);

            transaction.commit();

            System.out.println(
                    "Scholarship added successfully."
            );

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public Scholarship getScholarshipById(int id) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.get(
                    Scholarship.class,
                    id
            );
        }
    }

    @Override
    public List<Scholarship> getAllScholarships() {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Scholarship",
                    Scholarship.class
            ).list();
        }
    }

    @Override
    public void updateScholarship(
            Scholarship scholarship) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.merge(scholarship);

            transaction.commit();

            System.out.println(
                    "Scholarship updated successfully."
            );

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void deleteScholarship(int id) {

        Transaction transaction = null;

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Scholarship scholarship =
                    session.get(
                            Scholarship.class,
                            id
                    );

            if (scholarship != null) {

                session.remove(scholarship);

                System.out.println(
                        "Scholarship deleted successfully."
                );

            } else {

                System.out.println(
                        "Scholarship not found."
                );
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public List<Scholarship> findByState(
            String state) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Scholarship WHERE state = :state",
                            Scholarship.class
                    )
                    .setParameter(
                            "state",
                            state
                    )
                    .list();
        }
    }

    @Override
    public List<Scholarship> findByCategory(
            String category) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Scholarship WHERE category = :category",
                            Scholarship.class
                    )
                    .setParameter(
                            "category",
                            category
                    )
                    .list();
        }
    }

    @Override
    public List<Scholarship> findByIncomeLimit(
            double incomeLimit) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Scholarship WHERE incomeLimit >= :incomeLimit",
                            Scholarship.class
                    )
                    .setParameter(
                            "incomeLimit",
                            incomeLimit
                    )
                    .list();
        }
    }

    @Override
    public List<Scholarship> findByScholarshipName(
            String scholarshipName) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Scholarship WHERE scholarshipName LIKE :name",
                            Scholarship.class
                    )
                    .setParameter(
                            "name",
                            "%" + scholarshipName + "%"
                    )
                    .list();
        }
    }

    @Override
    public boolean existsByScholarshipName(
            String scholarshipName) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            Long count = session.createQuery(
                            "SELECT COUNT(s) " +
                                    "FROM Scholarship s " +
                                    "WHERE s.scholarshipName = :name",
                            Long.class
                    )
                    .setParameter(
                            "name",
                            scholarshipName
                    )
                    .uniqueResult();

            return count != null && count > 0;
        }
    }

    // =========================================================
    // FIND EXISTING SCHOLARSHIP
    // =========================================================

    @Override
    public Scholarship findExistingScholarship(
            String scholarshipName) {

        try (Session session =
                     HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                            "FROM Scholarship " +
                                    "WHERE scholarshipName = :name",
                            Scholarship.class
                    )
                    .setParameter(
                            "name",
                            scholarshipName
                    )
                    .uniqueResult();
        }
    }
}
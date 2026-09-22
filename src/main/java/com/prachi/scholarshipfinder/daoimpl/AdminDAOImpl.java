package com.prachi.scholarshipfinder.daoimpl;

import com.prachi.scholarshipfinder.dao.AdminDAO;
import com.prachi.scholarshipfinder.entity.Admin;
import com.prachi.scholarshipfinder.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl implements AdminDAO {

    @Override
    public Admin login(String username, String password) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            String hql = "FROM Admin WHERE username = :username AND password = :password";

            Query<Admin> query = session.createQuery(hql, Admin.class);

            query.setParameter("username", username);
            query.setParameter("password", password);

            return query.uniqueResult();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
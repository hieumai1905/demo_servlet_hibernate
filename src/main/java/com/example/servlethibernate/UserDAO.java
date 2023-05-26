package com.example.servlethibernate;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/*
Sử dụng SessionFactory để quản lý các phiên Hibernate, giúp đảm bảo rằng các phiên được tạo và hủy đúng cách.
Sử dụng try-with-resources để tự động giải phóng tài nguyên, giảm thiểu rủi ro xảy ra lỗi do quên giải phóng tài nguyên.
Sử dụng TypedQuery để truy vấn cơ sở dữ liệu, giúp đảm bảo rằng các truy vấn được kiểm tra cú pháp và loại truy vấn trước khi thực thi.
*/
public class UserDAO {
    private SessionFactory sessionFactory;

    public UserDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean save(User user) {
        // Lưu đối tượng User vào database
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return true;
        }
    }

    public User findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    public boolean update(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
    }

    public boolean delete(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            return true;
        }
    }

    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery("select u from User u", User.class);
            return query.getResultList();
        }
    }
}

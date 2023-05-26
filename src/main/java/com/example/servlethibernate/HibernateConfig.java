package com.example.servlethibernate;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig implements ServletContextListener {
    private SessionFactory sessionFactory;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Đường dẫn đến tệp cấu hình Hibernate
        String pathConfig = "hibernate.cfg.xml";

        // Tạo đối tượng Configuration và cấu hình từ tệp cấu hình
        Configuration configuration = new Configuration().configure(pathConfig);

        // Tạo đối tượng SessionFactory từ Configuration
        sessionFactory = configuration.buildSessionFactory();

        // Lưu đối tượng SessionFactory vào ServletContext để các Servlet khác có thể sử dụng
        sce.getServletContext().setAttribute("sessionFactory", sessionFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessionFactory.close();
    }
}

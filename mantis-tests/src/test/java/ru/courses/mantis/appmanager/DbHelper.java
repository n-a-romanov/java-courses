package ru.courses.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.courses.mantis.model.UserData;
import ru.courses.mantis.model.Users;


import java.util.List;

public class DbHelper extends HelperBase{

    private final SessionFactory sessionFactory;

    public DbHelper(ApplicationManager app) {
        super(app);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

   public Users users() {
        Session session = (sessionFactory).openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("FROM UserData").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}

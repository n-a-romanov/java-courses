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
        List<UserData> result = session.createQuery("FROM UserData WHERE access_level = 25 AND password = '5f4dcc3b5aa765d61d8327deb882cf99'").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}

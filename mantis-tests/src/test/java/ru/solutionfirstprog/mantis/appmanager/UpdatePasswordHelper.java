package ru.solutionfirstprog.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.openqa.selenium.By;
import ru.solutionfirstprog.mantis.model.UserHb;

import java.util.List;

public class UpdatePasswordHelper extends HelperBase{

    public UpdatePasswordHelper(ApplicationManager app) {
        super(app);
    }
    public void login() {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), "administrator");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        type(By.name("password"), "root");
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }

    public void searchUserList(){
        click(By.linkText("Управление"));
        click(By.linkText("Управление пользователями"));
        List<UserHb> usersList = app.updatePass().getUserInAdminForm();

    }

    public List<UserHb> getUserInAdminForm() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserHb> result = session.createQuery("from UserHB where username != 'administrator'").list(); //exclude admin by username
        session.getTransaction().commit();
        session.close();
        return result;
    }

}

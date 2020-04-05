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
    public void login(String admin, String pass) {
        wd.get(app.getProperty("web.baseUrl") + "/login.php");
        type(By.name("username"), admin);
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        type(By.name("password"), pass);
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }

    public void searchUserList(){
        click(By.linkText("Управление"));
        click(By.linkText("Управление пользователями"));
        List<UserHb> usersList = app.updatePass().getUserInAdminForm();
        click(By.linkText((usersList.iterator().next().getUsername())));
        click(By.xpath("//input[@value='Сбросить пароль']"));
        click(By.linkText("Продолжить"));
    }

    public List<UserHb> getUserInAdminForm() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserHb> result = session.createQuery("from UserHb where username != 'administrator'").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("span.bigger-110"));
    }
}

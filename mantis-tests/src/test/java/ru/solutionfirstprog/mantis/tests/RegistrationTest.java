package ru.solutionfirstprog.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.solutionfirstprog.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        applicationManager.mail().start();
    }
    @Test
    public void testRegistration() throws IOException, MessagingException {
        //long now = System.currentTimeMillis();
        //String user1 = String.format("mantisUser%s", now);
        String user1 = "erta1233";
        String password = "erta1233";
        //String email = String.format("mantisUser%s@localhost", now);
        String email = "erta1233@localhost.localdomain";
        //applicationManager.james().createUser(user1, password);
        applicationManager.registration().start(user1, email);
        List<MailMessage> mailMessages = applicationManager.mail().waitForMail(2, 10000);
        //List<MailMessage> mailMessages = applicationManager.james().waitForMail(user1, password,60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        applicationManager.registration().finish(confirmationLink, password);
        //assertTrue(applicationManager.httpSession().login(user1));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        applicationManager.mail().stop();
    }
}

package ru.solutionfirstprog.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.solutionfirstprog.mantis.model.MailMessage;
import ru.solutionfirstprog.mantis.model.UserHb;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;


public class UpdatePasswordTest extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        applicationManager.mail().start();
    }
    @Test
    public void testUpdatePassword() throws IOException, MessagingException {
        applicationManager.updatePass().login("administrator", "root");
        applicationManager.updatePass().searchUserList();
        List<UserHb> list = applicationManager.updatePass().getUserInAdminForm();
        //String name = list.iterator().next().getUsername();
        String email = list.iterator().next().getEmail();
        //String password = list.iterator().next().getPassword();
        List<MailMessage> mailMessages = applicationManager.mail().waitForMail(2, 60000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        applicationManager.updatePass().finish(confirmationLink, "test");
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

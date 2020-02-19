package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.solutionfirstprog.addressbook.module.GroupInf;

public class GroupHelper extends Helperbase{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroup() {
      click(By.name("submit"));
    }

    public void creationGroup(GroupInf groupInf) {
        type(By.name("group_name"), groupInf.getName());
        type(By.name("group_header"), groupInf.getHeader());
        type(By.name("group_footer"), groupInf.getFeeder());
        click(By.id("container"));
    }

    public void initNewGroup() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }
}

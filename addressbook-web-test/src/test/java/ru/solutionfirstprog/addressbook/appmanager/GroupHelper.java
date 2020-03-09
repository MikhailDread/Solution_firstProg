package ru.solutionfirstprog.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.solutionfirstprog.addressbook.module.GroupInf;

import java.util.ArrayList;
import java.util.List;

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


    public void modify(int index, GroupInf group) {
        selectGroup(index);
        initGroupModification();
        creationGroup(group);
        submitGroupModification();
        returnToGroupPage();
    }

    public void initNewGroup() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void create(GroupInf group) {
        initNewGroup();
        creationGroup(group);
        submitGroup();
        returnToGroupPage();
    }

    public void delete(int index) {
        selectGroup(index);
        deleteGroup();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {

        return isElementPresent(By.name("selected[]"));
    }

    public int groupCount(){
        return driver.findElements(By.name("selected[]")).size();
    }

    public List<GroupInf> list() {
        List<GroupInf> group = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
        for(WebElement e : elements){
            String name = e.getText();
            int id = Integer.parseInt(e.findElement(By.tagName("input")).getAttribute("value"));
            GroupInf user = new GroupInf(id, name, null, null);
            group.add(user);
        }
        return group;
    }

}

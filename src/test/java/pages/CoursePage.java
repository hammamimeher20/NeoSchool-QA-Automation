package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CoursePage {

    WebDriver driver;
    WebDriverWait wait;

    public CoursePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private final By boutonNewCourse =
            By.xpath("//a[contains(@href,'courses/create')]");

    private final By champSubject =
            By.id("data.subject_id");

    private final By champLevel =
            By.id("data.level_id");

    private final By champName =
            By.id("data.name");

    private final By boutonCreate =
            By.xpath("//button[contains(@class,'fi-ac-btn-action')]");

    private final By messageSuccess =
            By.xpath("//*[contains(text(),'Created') " +
                     "or contains(@class,'success')]");

    public void cliquerSurNewCourse() {
        wait.until(ExpectedConditions.elementToBeClickable(
                boutonNewCourse)).click();
    }

    public void selectionnerPremierSubject() {
        WebElement select = wait.until(
                ExpectedConditions.visibilityOfElementLocated(champSubject));
        Select dropdown = new Select(select);
        // sélectionner le premier subject disponible
        dropdown.selectByIndex(1);
    }

    public void selectionnerNiveau(String niveau) {
        WebElement select = wait.until(
                ExpectedConditions.visibilityOfElementLocated(champLevel));
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText(niveau);
    }

    public void saisirNom(String nom) {
        WebElement champ = wait.until(
                ExpectedConditions.visibilityOfElementLocated(champName));
        champ.clear();
        champ.sendKeys(nom);
    }

    public void cliquerSurCreate() {
        WebElement btn = wait.until(
                ExpectedConditions.elementToBeClickable(boutonCreate));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }

    public boolean isCourseCree() {
        try {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(messageSuccess))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

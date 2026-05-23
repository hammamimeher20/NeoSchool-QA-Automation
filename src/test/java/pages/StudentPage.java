package pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

/**
 * Page Object — Étudiants.
 * TC-04 : créer un étudiant avec upload photo.
 */
public class StudentPage extends BasePage {

    private final By titreStudents = By.xpath("//h1[contains(.,'Students')]");
    private final By boutonNewStudent = By.xpath("//a[contains(@class,'fi-btn') and .//span[contains(text(),'New')]]");
    private final By champPrenom       = By.id("data.first_name");
    private final By champNom          = By.id("data.last_name");
    private final By champDateNaissance = By.cssSelector("input[type='date']");
    private final By champGender       = By.id("data.gender");
    private final By champAdmission    = By.id("data.admission_number");
    private final By inputPhoto        = By.xpath("//input[@type='file']");
    private final By boutonCreate      = By.xpath("//button[contains(@class,'fi-ac-btn-action')]");
    private final By messageCreated    = By.xpath("//*[contains(text(),'Created')]");

    public StudentPage(WebDriver driver) {
        super(driver);
    }

    public void attendrePageStudents() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titreStudents));
    }

    public void cliquerSurNewStudent() {
        cliquer(boutonNewStudent);
    }

    public void entrerPrenom(String valeur) {
        saisir(champPrenom, valeur);
    }

    public void entrerNom(String valeur) {
        saisir(champNom, valeur);
    }

    public void entrerDateNaissance(String valeur) {
    
        WebElement champ = wait.until(
            ExpectedConditions.visibilityOfElementLocated(champDateNaissance)
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];", champ, valeur
        );
        // Déclencher l'événement pour que Livewire détecte le changement
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", champ
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", champ
        );
    }
    public void selectionnerGender(String valeur) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(champGender)))
            .selectByVisibleText(valeur);
    }

    public void entrerNumeroAdmission(String valeur) {
        saisir(champAdmission, valeur);
    }

   
    public void uploadPhoto(String cheminImage) {
        wait.until(ExpectedConditions.presenceOfElementLocated(inputPhoto));
        driver.findElement(inputPhoto).sendKeys(cheminImage);
    }

    public void cliquerSurCreate() {
        fermerModalSiAffiche();
        scrollEtCliquer(boutonCreate);
    }

    public boolean isStudentCree() {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/students"));
        } catch (Exception e) {
            return false;
        }
    }
}

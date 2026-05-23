package pages;
 
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
 
/**
 * Page Object — Enseignants.
 * TC-05 : créer un enseignant avec upload photo.
 */
public class TeacherPage extends BasePage {
 
    private final By boutonNewTeacher = By.xpath("//a[contains(@class,'fi-btn') and .//span[contains(text(),'New')]]");
    private final By champPrenom      = By.id("data.first_name");
    private final By champNom         = By.id("data.last_name");
    private final By champDateNaiss   = By.id("data.dob");
    private final By champEmail       = By.id("data.email");
    private final By champGender      = By.id("data.gender");
    private final By champPhone       = By.id("data.phone");
    private final By champCodePostal  = By.id("data.postal_code");
    private final By champAdresse     = By.id("data.address");
    private final By champStatus      = By.id("data.status");
    private final By inputPhoto       = By.xpath("//input[@type='file']");
    private final By boutonCreate     = By.xpath("//button[.//span[@class='fi-btn-label'][normalize-space()='Create']]");
    private final By messageCreated   = By.xpath("//*[contains(text(),'Created')]");
 
    public TeacherPage(WebDriver driver) {
        super(driver);
    }
 
    public void cliquerSurNewTeacher() {
        cliquer(boutonNewTeacher);
    }
 
    public void entrerPrenom(String valeur) {
        saisir(champPrenom, valeur);
    }
 
    public void entrerNom(String valeur) {
        saisir(champNom, valeur);
    }
 
    /**
     * Fix date : sendKeys mélange les caractères sur les champs date en locale FR.
     * On force la valeur via JavaScript (format ISO : yyyy-MM-dd).
     */
    public void entrerDateNaissance(String valeur) {
        WebElement champ = wait.until(
            ExpectedConditions.visibilityOfElementLocated(champDateNaiss)
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];", champ, valeur
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", champ
        );
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", champ
        );
    }
 
    public void entrerEmail(String valeur) {
        saisir(champEmail, valeur);
    }
 
    public void selectionnerGender(String valeur) {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(champGender)))
            .selectByVisibleText(valeur);
    }
 
    /**
     * Fix phone : le champ attend le format international +216XXXXXXXX.
     */
    public void entrerTelephone(String valeur) {
        saisir(champPhone, valeur);
    }
 
    public void entrerCodePostal(String valeur) {
        saisir(champCodePostal, valeur);
    }
 
    public void entrerAdresse(String valeur) {
        saisir(champAdresse, valeur);
    }
 
    public void selectionnerStatutActif() {
        new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(champStatus)))
            .selectByValue("active");
    }
 
    /**
     * Upload de la photo de l'enseignant via sendKeys sur l'input file.
     * Fournir le chemin absolu : System.getProperty("user.dir") + "/src/test/resources/images/teacher.jpg"
     */
    public void uploadPhoto(String cheminImage) {
        wait.until(ExpectedConditions.presenceOfElementLocated(inputPhoto));
        driver.findElement(inputPhoto).sendKeys(cheminImage);
    }
 
    public void cliquerSurCreate() {
        fermerModalSiAffiche();
        scrollEtCliquer(boutonCreate);
    }
 
    public boolean isTeacherCree() {
        try {
            // Le toast disparaît trop vite → on valide par la redirection vers la liste
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.urlContains("/teachers"));
        } catch (Exception e) {
            return false;
        }
    }
}
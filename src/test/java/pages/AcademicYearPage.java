package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
 
/**
 * Page Object — Années académiques.
 * TC-02 : créer une année académique.
 * TC-09 : définir une année comme courante.
 */
public class AcademicYearPage extends BasePage {
 
    private final By boutonNewAcademicYear = By.xpath("//a[contains(@href,'academic-years/create')]");
    private final By champDetails          = By.xpath("//textarea[contains(@x-model,'state')]");
    private final By champStartDate        = By.id("data.starts_at");
    private final By champEndDate          = By.id("data.ends_at");
    private final By toggleImportData      = By.xpath("//button[@role='switch']");
    private final By boutonCreate          = By.xpath("//form//button[.//span[normalize-space()='Create']]");
    private final By messageSucces         = By.xpath("//*[contains(text(),'created successfully')]");
    private final By messageSwitched       = By.xpath("//*[contains(text(),'Switched Successfully')]");
 
    public AcademicYearPage(WebDriver driver) {
        super(driver);
    }
 
    public void cliquerSurNewAcademicYear() {
        cliquer(boutonNewAcademicYear);
    }
 
    /**
     * Sélectionne l'année académique dans le dropdown custom (Choices.js).
     * Fix StaleElementReferenceException : re-chercher l'option APRÈS l'ouverture.
     */
    public void entrerAnneeAcademique(String valeur) {
        // 1. Ouvrir le dropdown
        By innerDiv = By.cssSelector("div.choices__inner");
        wait.until(ExpectedConditions.elementToBeClickable(innerDiv)).click();
 
        // 2. Attendre que les options soient chargées dans le DOM
        By option = By.cssSelector("div[data-value='" + valeur + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(option));
 
        // 3. Re-chercher et cliquer (évite le StaleElementReferenceException)
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(option));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);
    }
 
    public void entrerDetails(String valeur) {
        saisir(champDetails, valeur);
    }
 
    public String getStartDate() {
        return obtenirAttribut(champStartDate, "value");
    }
 
    public String getEndDate() {
        return obtenirAttribut(champEndDate, "value");
    }
 
    public void desactiverImportData() {
        WebElement toggle = wait.until(ExpectedConditions.visibilityOfElementLocated(toggleImportData));
        if ("true".equals(toggle.getAttribute("aria-checked"))) {
            toggle.click();
        }
    }
 
    public void cliquerSurCreate() {
        scrollEtCliquer(boutonCreate);
    }
 
    public boolean isMessageSuccesAffiche() {
        return estVisible(messageSucces);
    }
 
    /** Vérifie si l'année académique existe déjà dans la liste. */
    public boolean anneeExisteDejaDansListe(String annee) {
        try {
            return !driver.findElements(
                By.xpath("//*[contains(normalize-space(),'" + annee + "')]")
            ).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
 
    /**
     * TC-09 : Définit une année comme courante.
     */
    public void setAcademicYearAsCurrent(String annee) {
        By ligneAnnee   = By.xpath("//tr[.//td[contains(normalize-space(),'" + annee + "')]]");
        By boutonAction = By.xpath("//tr[.//td[contains(normalize-space(),'" + annee + "')]]//button");
        By boutonCurrent = By.xpath("//*[contains(text(),'Current')]");
        By modal        = By.xpath("//div[contains(@class,'fi-modal')]");
        By boutonConfirm = By.xpath("//button[contains(.,'Confirm')]");
 
        wait.until(ExpectedConditions.visibilityOfElementLocated(ligneAnnee));
        WebElement btnAction = wait.until(ExpectedConditions.elementToBeClickable(boutonAction));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnAction);
 
        WebElement btnCurrent = wait.until(ExpectedConditions.elementToBeClickable(boutonCurrent));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnCurrent);
 
        wait.until(ExpectedConditions.visibilityOfElementLocated(modal));
        WebElement btnConfirm = wait.until(ExpectedConditions.elementToBeClickable(boutonConfirm));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnConfirm);
    }
 
    public void confirmer() {
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[.//span[normalize-space()='Confirm']]")
        )).click();
    }
 
    public boolean isAnneeSwitchee() {
        return estVisible(messageSwitched);
    }
}
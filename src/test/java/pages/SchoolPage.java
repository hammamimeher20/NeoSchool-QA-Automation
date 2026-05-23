package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SchoolPage {

    WebDriver driver;
    WebDriverWait wait;

    public SchoolPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // TODO: Remplacer les locators par les vrais après inspection de la page
    private final By BouttonNewSchool = By.xpath("//button[contains(text(),'New schools')]");
    private final By ChampRecherche   = By.xpath("//input[@type='search']");
    private final By BouttonEdit      = By.xpath("//button[contains(text(),'Edit')]");
    private final By ChampTelephone   = By.id("phone");
    private final By ChampCodePostal  = By.id("postal_code");
    private final By ChampAdresse     = By.id("address");
    private final By ChampDetails     = By.id("details");
    private final By BouttonCreate    = By.xpath("//button[contains(text(),'Create')]");
    private final By MessageErreur    = By.xpath("//*[contains(@class,'error') or contains(@class,'invalid')]");

    // cliquer sur le bouton New schools
    public void cliquerSurNewSchool() {
        wait.until(ExpectedConditions.elementToBeClickable(BouttonNewSchool)).click();
    }

    // rechercher une école
    public void rechercherEcole(String nom) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampRecherche)).sendKeys(nom);
    }

    // cliquer sur le bouton Edit de l'école
    public void cliquerSurEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(BouttonEdit)).click();
    }

    // saisir le téléphone
    public void entrerTelephone(String valeur) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampTelephone)).sendKeys(valeur);
    }

    // saisir le code postal
    public void entrerCodePostal(String valeur) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampCodePostal)).sendKeys(valeur);
    }

    // saisir l'adresse
    public void entrerAdresse(String valeur) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampAdresse)).sendKeys(valeur);
    }

    // saisir les détails
    public void entrerDetails(String valeur) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(ChampDetails)).sendKeys(valeur);
    }

    // cliquer sur le bouton Create
    public void cliquerSurCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(BouttonCreate)).click();
    }

    // vérifie que le message d'erreur s'affiche
    public boolean isMessageErreurAffiche() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(MessageErreur)).isDisplayed();
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EditAdminPage {

    WebDriver driver;
    WebDriverWait wait;

    public EditAdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    
    private final By ChampPrenom    = By.id("first_name");
    private final By ChampNom       = By.id("last_name");
    private final By ChampEmail     = By.id("email");
    private final By ChampTelephone = By.id("phone");
    private final By ChampAdresse   = By.id("address");
    private final By BouttonEdit    = By.xpath("//button[contains(text(),'Edit')]");

   
    public void modifierPrenom(String valeur) {
        var champ = wait.until(ExpectedConditions.visibilityOfElementLocated(ChampPrenom));
        champ.clear();
        champ.sendKeys(valeur);
    }

    
    public void modifierNom(String valeur) {
        var champ = wait.until(ExpectedConditions.visibilityOfElementLocated(ChampNom));
        champ.clear();
        champ.sendKeys(valeur);
    }

    
    public void modifierEmail(String valeur) {
        var champ = wait.until(ExpectedConditions.visibilityOfElementLocated(ChampEmail));
        champ.clear();
        champ.sendKeys(valeur);
    }

  
    public void modifierTelephone(String valeur) {
        var champ = wait.until(ExpectedConditions.visibilityOfElementLocated(ChampTelephone));
        champ.clear();
        champ.sendKeys(valeur);
    }

    
    public void modifierAdresse(String valeur) {
        var champ = wait.until(ExpectedConditions.visibilityOfElementLocated(ChampAdresse));
        champ.clear();
        champ.sendKeys(valeur);
    }

    
    public void cliquerSurEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(BouttonEdit)).click();
    }
}

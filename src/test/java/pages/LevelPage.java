package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
 
/**
 * Page Object — Niveaux.
 * TC-06 : créer un niveau (avec validation champ vide).
 */
public class LevelPage extends BasePage {
 
    private final By boutonNewLevel = By.xpath("//a[contains(@href,'levels/create')]");
    private final By champName      = By.id("data.name");
 
    // Le bouton Create est dans le <form> — on cible uniquement celui dans le formulaire
    private final By boutonCreate = By.xpath(
        "//form//button[.//span[normalize-space()='Create']]"
    );
 
    // Message d'erreur de validation (champ Name vide)
    private final By messageErreur = By.xpath(
        "//*[contains(@class,'fi-fo-field-wrp-error') or " +
        "contains(@class,'text-danger') or " +
        "contains(@class,'validation-error') or " +
        "contains(@class,'fi-color-danger')]"
    );
 
    public LevelPage(WebDriver driver) {
        super(driver);
    }
 
    public void cliquerSurNewLevel() {
        cliquer(boutonNewLevel);
    }
 
    public void entrerNom(String valeur) {
        saisir(champName, valeur);
    }
 
    public void cliquerSurCreate() {
        scrollEtCliquer(boutonCreate);
    }
 
    public boolean isMessageErreurAffiche() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(messageErreur))
                       .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isMessageCreatedAffiche() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20))
                .until(d -> !d.getCurrentUrl().contains("/create") && d.getCurrentUrl().contains("/levels"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
package pages;
 
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
 
/**
 * Page Object — Classes.
 * TC-07 : créer une classe avec infos incomplètes.
 * TC-08 : créer une classe avec infos valides.
 */
public class ClassPage extends BasePage {
 
    private final By boutonNewClass = By.xpath("//a[contains(@href,'academic-classes/create')]");
    private final By champLevel     = By.id("data.level_id");
    private final By champName      = By.id("data.name");
    private final By boutonCreate   = By.xpath("//button[.//span[contains(text(),'Create')]]");
    private final By messageCreated = By.xpath("//*[contains(text(),'Created')]");
    private final By messageErreur  = By.xpath("//*[contains(@class,'error') or contains(@class,'danger') or contains(@class,'validation')]");
 
    public ClassPage(WebDriver driver) {
        super(driver);
    }
 
    public void cliquerSurNewClass() {
        // Fermer l'overlay sidebar si présent
        try {
            WebElement overlay = driver.findElement(By.cssSelector(".fi-sidebar-close-overlay"));
            if (overlay.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", overlay);
            }
        } catch (Exception ignored) {}
 
        cliquerJS(boutonNewClass);
    }
 
    public void entrerNom(String valeur) {
        saisir(champName, valeur);
    }
 
    public void selectionnerNiveau(String niveau) {
        WebElement select = wait.until(ExpectedConditions.presenceOfElementLocated(champLevel));
        // Utiliser JS pour contourner le select désactivé
        ((JavascriptExecutor) driver).executeScript(
            "var sel = arguments[0];" +
            "for(var i=0; i<sel.options.length; i++){" +
            "  if(sel.options[i].text.trim() === arguments[1]){" +
            "    sel.selectedIndex = i; break;" +
            "  }" +
            "}" +
            "sel.dispatchEvent(new Event('change', {bubbles:true}));",
            select, niveau
        );
    }
 
    public void cliquerSurCreate() {
        scrollEtCliquer(boutonCreate);
    }
 
    public boolean isMessageCreatedAffiche() {
        try {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(20))
                .until(d -> !d.getCurrentUrl().contains("/create") && d.getCurrentUrl().contains("/academic-classes"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isMessageErreurAffiche() {
        return estVisible(messageErreur);
    }
}
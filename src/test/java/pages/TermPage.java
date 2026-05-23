package pages;
 
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
 
public class TermPage extends BasePage {
 
    private WebDriverWait wait15;
 
    private final By bouttonNewTerm   = By.xpath("//a[contains(@href,'terms/create')]");
    private final By champName        = By.id("data.name");
    private final By champDescription = By.xpath("//textarea[contains(@x-model,'state')]");
    private final By bouttonCreate    = By.xpath("//button[@type='submit' and contains(@class,'fi-ac-btn-action')]");
    private final By messageCreated   = By.xpath("//*[contains(text(),'Created')]");
 
    public TermPage(WebDriver driver) {
        super(driver);
        this.wait15 = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
 
    public void cliquerSurTerms() {
        cliquerJS(By.xpath("//span[normalize-space()='Terms']"));
    }
 
    public void cliquerSurNewTerm() {
        wait15.until(ExpectedConditions.elementToBeClickable(bouttonNewTerm)).click();
    }
 
    public void entrerNom(String valeur) {
        WebElement champ = wait15.until(ExpectedConditions.visibilityOfElementLocated(champName));
        champ.clear();
        champ.sendKeys(valeur);
    }
 
    public void entrerDescription(String valeur) {
        WebElement champ = wait15.until(ExpectedConditions.visibilityOfElementLocated(champDescription));
        champ.clear();
        champ.sendKeys(valeur);
    }
 
    private void selectionnerDate(String inputId, String date) {
        String[] parts = date.split("/");
        String jour  = String.valueOf(Integer.parseInt(parts[0]));
        String mois  = String.valueOf(Integer.parseInt(parts[1]) - 1);
        String annee = parts[2];
 
        String parentXpath = "//input[@id='" + inputId + "']/parent::button/parent::div";
 
        By boutonOuvrir = By.xpath("//input[@id='" + inputId + "']/parent::button");
        wait15.until(ExpectedConditions.elementToBeClickable(boutonOuvrir)).click();
 
        By panel = By.xpath(parentXpath + "//div[@x-ref='panel']");
        wait15.until(ExpectedConditions.visibilityOfElementLocated(panel));
 
        By selectMois = By.xpath(parentXpath + "//div[@x-ref='panel']//select[contains(@x-model,'focusedMonth')]");
        new Select(wait15.until(ExpectedConditions.visibilityOfElementLocated(selectMois)))
            .selectByValue(mois);
 
        By inputAnnee = By.xpath(parentXpath + "//div[@x-ref='panel']//input[@type='number' and contains(@inputmode,'numeric')]");
        WebElement anneeField = wait15.until(ExpectedConditions.visibilityOfElementLocated(inputAnnee));
        anneeField.clear();
        anneeField.sendKeys(annee);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", anneeField
        );
        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
 
        By dayLocator = By.xpath(
            parentXpath + "//div[@x-ref='panel']" +
            "//div[@role='grid']//div[normalize-space()='" + jour +
            "' and not(contains(@class,'opacity'))]"
        );
        wait15.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
    }
 
    public void entrerStartsAt(String date) {
        selectionnerDate("data.starts_at", date);
    }
 
    public void entrerEndsAt(String date) {
        selectionnerDate("data.ends_at", date);
    }
 
    public void cliquerSurCreate() {
        WebElement btn = wait15.until(
            ExpectedConditions.elementToBeClickable(bouttonCreate)
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }
 
    public boolean isMessageCreatedAffiche() {
        try {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(20))
                .until(d -> !d.getCurrentUrl().contains("/create") && d.getCurrentUrl().contains("/terms"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 
    public boolean isTermPresent(String nom) {
        try {
            return driver.findElements(
                org.openqa.selenium.By.xpath("//*[contains(text(),'" + nom + "')]")
            ).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
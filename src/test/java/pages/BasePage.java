package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Classe de base pour toutes les Page Objects.
 * Centralise driver, wait et les opérations communes.
 * Toutes les pages héritent de cette classe.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Attend qu'un élément soit cliquable puis clique dessus. */
    protected void cliquer(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /** Clique via JavaScript (pour les composants Livewire/Alpine). */
    protected void cliquerJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /** Attend la visibilité, efface et saisit le texte. */
    protected void saisir(By locator, String texte) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(texte);
    }

    /** Retourne la valeur d'un attribut d'un élément. */
    protected String obtenirAttribut(By locator, String attribut) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                   .getAttribute(attribut);
    }

    /** Retourne le texte d'un élément. */
    protected String obtenirTexte(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
    }

    /** Vérifie si un élément est visible. */
    protected boolean estVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Scroll vers un élément avant de cliquer. */
    protected void scrollEtCliquer(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /** Ferme le modal s'il est affiché. */
    protected void fermerModalSiAffiche() {
        try {
            By modal = By.xpath("//div[contains(@class,'fi-modal-header')]");
            By btnClose = By.xpath("//button[normalize-space()='Close']");
            if (!driver.findElements(modal).isEmpty()) {
                new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(btnClose)).click();
            }
        } catch (Exception ignored) {}
    }
}

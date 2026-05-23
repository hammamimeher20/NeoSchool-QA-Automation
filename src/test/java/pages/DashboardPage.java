package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

/**
 * Page Object — Dashboard NeoSchool.
 * Centralise la navigation vers tous les menus.
 */
public class DashboardPage extends BasePage {

    private final By titreDashboard = By.xpath("//h1[contains(@class,'fi-header-heading')]");
    private final By modalContainer = By.xpath("//div[contains(@class,'fi-modal') or contains(@x-ref,'modalContainer')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean estDashboardAffiche() {
        return estVisible(titreDashboard);
    }

    /** Clique sur un menu latéral par son libellé. */
    public void clickMenu(String libelle) {
        // Attendre que tout modal soit fermé avant navigation
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(modalContainer));
        } catch (Exception ignored) {}

        By menuItem = By.xpath("//span[contains(@class,'fi-sidebar-item-label') and normalize-space()='" + libelle + "']");
        cliquerJS(menuItem);
    }

    public void cliquerSurAcademicYears() { clickMenu("Academic Years"); }
    public void cliquerSurTerms()         { clickMenu("Terms"); }
    public void cliquerSurStudents()      { clickMenu("Students"); }
    public void cliquerSurTeachers()      { clickMenu("Teachers"); }
    public void cliquerSurLevels()        { clickMenu("Levels"); }
    public void cliquerSurClasses()       { clickMenu("Classes"); }
    public void cliquerSurSchools()       { clickMenu("Schools"); }
}

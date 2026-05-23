package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
/**
 * Page Object — Page de connexion NeoSchool.
 */
public class LoginPage extends BasePage {
    // By.id() avec des points génère un sélecteur CSS cassé (#data.school_code)
    // → XPath contourne ce problème en ciblant l'attribut directement
    private final By champSchoolCode = By.xpath("//*[@id='data.school_code']");
    private final By champEmail      = By.xpath("//*[@id='data.email']");
    private final By champMotDePasse = By.xpath("//*[@id='data.password']");
    private final By boutonLogin     = By.xpath("//button[normalize-space()='Sign in']");
    private final By titreDashboard  = By.xpath("//h1[contains(@class,'fi-header-heading') and contains(text(),'Admin Dashboard')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean estSchoolCodeAffiche() {
        return estVisible(champSchoolCode);
    }
    public boolean estEmailAffiche() {
        return estVisible(champEmail);
    }
    public boolean estMotDePasseAffiche() {
        return estVisible(champMotDePasse);
    }

    /** Effectue la connexion complète et attend le Dashboard. */
    public void seConnecter(String schoolCode, String email, String motDePasse) {
        saisir(champSchoolCode, schoolCode);
        saisir(champEmail, email);
        saisir(champMotDePasse, motDePasse);
        cliquer(boutonLogin);
        wait.until(ExpectedConditions.visibilityOfElementLocated(titreDashboard));
    }
}
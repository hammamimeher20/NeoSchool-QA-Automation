package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.DashboardPage;

import java.time.Duration;

/**
 * TC-01 — Se connecter à NeoSchool
 *
 * Ce test vérifie la connexion elle-même.
 * Il surcharge @BeforeMethod pour NE PAS se connecter automatiquement.
 */
public class TC01_SeConnecterANeoschool extends BaseTest {

    @Override
    @BeforeMethod
    public void ouvrirBrowserEtSeConnecter() {
        // Ouvrir le browser SANS se connecter — le test le fait lui-même
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @Test
    public void TC01_seConnecterANeoschool() {
        LoginPage loginPage = new LoginPage(driver);
        DashboardPage dashboardPage = new DashboardPage(driver);

        test = extent.createTest("TC01 - Se connecter à NeoSchool");

        // Vérifier que la page Sign in est affichée avec les 3 champs
        test.info("Vérifier que la page 'Neoschool Sign in' est affichée");
        Assert.assertTrue(loginPage.estSchoolCodeAffiche(),   "Le champ School Code doit être affiché");
        Assert.assertTrue(loginPage.estEmailAffiche(),        "Le champ Email doit être affiché");
        Assert.assertTrue(loginPage.estMotDePasseAffiche(),   "Le champ Password doit être affiché");

        // Se connecter avec des identifiants valides
        test.info("Se connecter avec des identifiants valides");
        loginPage.seConnecter(SCHOOL_CODE, EMAIL, MOT_DE_PASSE);

        // Vérifier que le Dashboard s'affiche
        test.info("Vérifier que le Dashboard s'affiche");
        Assert.assertTrue(dashboardPage.estDashboardAffiche(), "Le Dashboard doit être affiché après connexion");
    }
}

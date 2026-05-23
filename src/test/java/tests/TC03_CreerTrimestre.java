package tests;
 
import base.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AcademicYearPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.TermPage;
 
import java.time.Duration;
 
/**
 * TC-03 — Créer un trimestre
 * Prérequis : TC-01 et TC-09
 *
 
 */
public class TC03_CreerTrimestre extends BaseTest {
 
    // Définition des 3 trimestres possibles
    private static final String[][] TRIMESTRES = {
        {"Deuxième Trimestre",  "16/12/2026", "15/03/2027"},
        {"Troisième Trimestre", "16/03/2027", "30/06/2027"},
        {"Premier Trimestre",   "15/09/2026", "15/12/2026"}
    };
 
  
        @Test
        public void TC03_creerTrimestre() {
            AcademicYearPage academicPage  = new AcademicYearPage(driver);
            DashboardPage    dashboardPage = new DashboardPage(driver);
            TermPage         termPage      = new TermPage(driver);

            test = extent.createTest("TC03 - Créer un trimestre");

            test.info("Vérifier l'année courante 2026/2027");
            dashboardPage.cliquerSurAcademicYears();
            try { academicPage.setAcademicYearAsCurrent("2026/2027"); }
            catch (Exception ignored) {}

            test.info("Accéder à Terms");
            termPage.cliquerSurTerms();
 
        // Choisir le premier trimestre non encore présent dans la liste
        String nom      = null;
        String startsAt = null;
        String endsAt   = null;
 
        for (String[] t : TRIMESTRES) {
            if (!termPage.isTermPresent(t[0])) {
                nom      = t[0];
                startsAt = t[1];
                endsAt   = t[2];
                break;
            }
        }
 
        if (nom == null) {
            test.info("Tous les trimestres existent déjà — TC03 validé");
            return;
        }
 
        test.info("Cliquer New Terms");
        termPage.cliquerSurNewTerm();
 
        test.info("Saisir le nom : " + nom);
        termPage.entrerNom(nom);
 
        test.info("Saisir la description");
        termPage.entrerDescription(nom + " de l'année académique 2026/2027");
 
        test.info("Saisir Starts at : " + startsAt);
        termPage.entrerStartsAt(startsAt);
 
        test.info("Saisir Ends at : " + endsAt);
        termPage.entrerEndsAt(endsAt);
 
        test.info("Cliquer Create");
        termPage.cliquerSurCreate();
 
        test.info("Vérifier message Created");
        Assert.assertTrue(termPage.isMessageCreatedAffiche(),
            "Le message 'Created' doit s'afficher");
    }
}
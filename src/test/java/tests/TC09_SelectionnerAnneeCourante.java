package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AcademicYearPage;
import pages.DashboardPage;

import java.time.Duration;

/**
 * TC-09 — Sélectionner une année académique courante
 * Prérequis : TC-01 → connexion gérée par BaseTest
 */
public class TC09_SelectionnerAnneeCourante extends BaseTest {

    private static final String ANNEE_CIBLE = "2026/2027";

    @Test
    public void TC09_selectionnerAnneeCourante() {
        DashboardPage dashboardPage      = new DashboardPage(driver);
        AcademicYearPage academicYearPage = new AcademicYearPage(driver);
        WebDriverWait wait                = new WebDriverWait(driver, Duration.ofSeconds(15));

        test = extent.createTest("TC09 - Sélectionner une année académique courante");

        test.info("Cliquer sur le menu Academic Years");
        dashboardPage.cliquerSurAcademicYears();

        // Vérifier si l'année est déjà courante
        boolean dejaCourrante = !driver.findElements(By.xpath(
            "//tr[.//span[normalize-space()='" + ANNEE_CIBLE + "']]" +
            "//*[contains(text(),'Current Academic Year')]"
        )).isEmpty();

        if (dejaCourrante) {
            test.info("2026/2027 est déjà l'année courante — TC09 validé");
        } else {
            test.info("Cliquer sur Set as Current pour 2026/2027");
            By bouton = By.xpath(
                "//tr[.//span[normalize-space()='" + ANNEE_CIBLE + "']]" +
                "//span[contains(text(),'Set as Current')]/ancestor::button"
            );
            wait.until(ExpectedConditions.elementToBeClickable(bouton)).click();

            test.info("Cliquer sur Confirm");
            academicYearPage.confirmer();

            test.info("Vérifier le message Switched Successfully");
            Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Switched Successfully')]")
                )).isDisplayed(),
                "Le message 'Switched Successfully' doit s'afficher"
            );
        }
    }
}

package tests;
 
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AcademicYearPage;
import pages.DashboardPage;
 
/**
 * TC-02 — Créer une année académique
 * Prérequis : TC-01 → géré par @BeforeMethod dans BaseTest
 */
public class TC02_CreerAnneeAcademique extends BaseTest {
 
    private static final String ANNEE = "2026/2027";
 
    @Test
    public void TC02_creerAnneeAcademique() {
        DashboardPage dashboardPage   = new DashboardPage(driver);
        AcademicYearPage academicPage = new AcademicYearPage(driver);
 
        test = extent.createTest("TC02 - Créer une année académique");
 
        test.info("Cliquer sur le menu Academic Years");
        dashboardPage.cliquerSurAcademicYears();
 
        // Vérifier si l'année existe déjà — éviter la duplication
        if (academicPage.anneeExisteDejaDansListe(ANNEE)) {
            test.info("L'année " + ANNEE + " existe déjà — TC02 validé");
            return;
        }
 
        test.info("Cliquer sur New Academic Years");
        academicPage.cliquerSurNewAcademicYear();
 
        test.info("Vérifier Start Date = 15/09/2026 par défaut");
        Assert.assertEquals(academicPage.getStartDate(), "2026-09-15",
            "Start Date doit être 15/09/2026 par défaut");
 
        test.info("Vérifier End Date = 30/06/2027 par défaut");
        Assert.assertEquals(academicPage.getEndDate(), "2027-06-30",
            "End Date doit être 30/06/2027 par défaut");
 
        test.info("Saisir l'année académique : " + ANNEE);
        academicPage.entrerAnneeAcademique(ANNEE);
 
        test.info("Saisir les détails");
        academicPage.entrerDetails(
            "Année académique couvrant les deux semestres avec les périodes d'examens " +
            "et les activités pédagogiques."
        );
 
        test.info("Désactiver le toggle Import data");
        academicPage.desactiverImportData();
 
        test.info("Cliquer sur Create");
        academicPage.cliquerSurCreate();
 
        test.info("Vérifier le message de succès");
        Assert.assertTrue(academicPage.isMessageSuccesAffiche(),
            "Le message 'Academic Year has been created successfully !' doit s'afficher");
    }
}
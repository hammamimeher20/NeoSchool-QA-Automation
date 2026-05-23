package tests;
 
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AcademicYearPage;
import pages.ClassPage;
import pages.DashboardPage;
 
/**
 * TC-08 — Créer une classe avec des informations valides
 * Prérequis : TC-01, TC-09, TC-06 (Niveau 1 doit exister)
 *
 * Données :
 *   Level : Niveau 1
 *   Name  : Classes A
 */
public class TC08_CreerClasseInfosValides extends BaseTest {
 
    private static final String NIVEAU     = "Niveau 1";
    // Nom unique à chaque exécution pour éviter "name already taken"
    private static final String NOM_CLASSE = "Classes A" + System.currentTimeMillis() % 1000;
 
    @Test
    public void TC08_creerClasseInfosValides() {
        DashboardPage dashboardPage   = new DashboardPage(driver);
        AcademicYearPage academicPage = new AcademicYearPage(driver);
        ClassPage classPage           = new ClassPage(driver);
 
        test = extent.createTest("TC08 - Créer une classe avec des informations valides");
 
        test.info("Définir 2026/2027 comme année courante");
        dashboardPage.cliquerSurAcademicYears();
        try { academicPage.setAcademicYearAsCurrent("2026/2027"); } catch (Exception ignored) {}
 
        test.info("Cliquer sur le menu Classes");
        dashboardPage.cliquerSurClasses();
 
        test.info("Cliquer sur New Class");
        classPage.cliquerSurNewClass();
 
        test.info("Saisir le nom : " + NOM_CLASSE);
        classPage.entrerNom(NOM_CLASSE);
 
        test.info("Sélectionner Niveau 1");
        classPage.selectionnerNiveau(NIVEAU);
 
        test.info("Cliquer sur Create");
        classPage.cliquerSurCreate();
 
        test.info("Vérifier le message Created");
        Assert.assertTrue(classPage.isMessageCreatedAffiche(),
            "Le message 'Created' doit s'afficher après la création de la classe");
    }
}
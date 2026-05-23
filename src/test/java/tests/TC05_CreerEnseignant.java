package tests;
 
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.TeacherPage;
 
/**
 * TC-05 — Créer un enseignant
 * Prérequis : TC-01 et TC-09 → connexion gérée par BaseTest
 *
 * Données PDF :
 *   First Name   : Mohamed
 *   Last Name    : Ben Salah
 *   Date of Birth: 12/05/1985  → format ISO : 1985-05-12
 *   School Email : unique par timestamp
 *   Gender       : Male
 *   Phone        : +21659595959 (format international)
 *   Postal Code  : 2000
 *   Address      : 31 rue picaso
 *   Status       : Active
 *   Photo        : upload obligatoire
 */
public class TC05_CreerEnseignant extends BaseTest {
 
    private static final String PRENOM      = "Mohamed";
    private static final String NOM         = "Ben Salah";
    private static final String DATE_NAISS  = "1985-05-12";
    private static final String TELEPHONE   = "+21659595959";
    private static final String CODE_POSTAL = "2000";
    private static final String ADRESSE     = "31 rue picaso";
 
    // Email unique pour éviter les doublons entre exécutions
    private static final String EMAIL =
        "user" + System.currentTimeMillis() + "@futureacademy.com";
 
    // Placer teacher.jpg dans src/test/resources/images/
    private static final String CHEMIN_PHOTO =
        System.getProperty("user.dir") + "/src/test/resources/images/teacher.jpg";
 
    @Test
    public void TC05_creerEnseignant() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        TeacherPage teacherPage     = new TeacherPage(driver);
 
        test = extent.createTest("TC05 - Créer un enseignant");
 
        test.info("Cliquer sur le menu Teachers");
        dashboardPage.cliquerSurTeachers();
 
        test.info("Cliquer sur New Teacher");
        teacherPage.cliquerSurNewTeacher();
 
        test.info("Remplir le formulaire");
        teacherPage.entrerPrenom(PRENOM);
        teacherPage.entrerNom(NOM);
        teacherPage.entrerDateNaissance(DATE_NAISS);
        teacherPage.entrerEmail(EMAIL);
        teacherPage.selectionnerGender("Male");
        teacherPage.entrerTelephone(TELEPHONE);
        teacherPage.entrerCodePostal(CODE_POSTAL);
        teacherPage.entrerAdresse(ADRESSE);
        teacherPage.selectionnerStatutActif();
 
        test.info("Uploader la photo de l'enseignant");
        teacherPage.uploadPhoto(CHEMIN_PHOTO);
 
        test.info("Cliquer sur Create");
        teacherPage.cliquerSurCreate();
 
        test.info("Vérifier le message Created");
        Assert.assertTrue(teacherPage.isTeacherCree(),
            "Le message 'Created' doit s'afficher après la création de l'enseignant");
    }
}
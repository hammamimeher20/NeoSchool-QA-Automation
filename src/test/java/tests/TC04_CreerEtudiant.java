package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.StudentPage;

/**
 * TC-04 — Créer un étudiant
 * Prérequis : TC-01 et TC-09 → connexion gérée par BaseTest
 *
 */
public class TC04_CreerEtudiant extends BaseTest {

    private static final String PRENOM           = "Ahmed";
    private static final String NOM              = "Askri";
    private static final String DATE_NAISSANCE   = "2016-08-22";
    private static final String NUMERO_ADMISSION = "601";

    // Placer student.jpg dans src/test/resources/images/
    private static final String CHEMIN_PHOTO =
        System.getProperty("user.dir") + "/src/test/resources/images/student.jpg";

    @Test
    public void TC04_creerEtudiant() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        StudentPage studentPage     = new StudentPage(driver);

        test = extent.createTest("TC04 - Créer un étudiant");

        test.info("Cliquer sur le menu Students");
        dashboardPage.cliquerSurStudents();

        test.info("Attendre la page Students");
        studentPage.attendrePageStudents();

        test.info("Cliquer sur New Student");
        studentPage.cliquerSurNewStudent();

        test.info("Remplir le formulaire");
        studentPage.entrerPrenom(PRENOM);
        studentPage.entrerNom(NOM);
        studentPage.entrerDateNaissance(DATE_NAISSANCE);
        studentPage.selectionnerGender("Male");
        studentPage.entrerNumeroAdmission(NUMERO_ADMISSION);

        test.info("Uploader la photo de l'étudiant");
        studentPage.uploadPhoto(CHEMIN_PHOTO);

        test.info("Cliquer sur Create");
        studentPage.cliquerSurCreate();

        test.info("Vérifier le message Created");
        Assert.assertTrue(studentPage.isStudentCree(),
            "Le message 'Created' doit s'afficher après la création de l'étudiant");
    }
}

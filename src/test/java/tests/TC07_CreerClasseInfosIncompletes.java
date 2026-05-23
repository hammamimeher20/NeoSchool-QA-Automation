package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ClassPage;
import pages.DashboardPage;

/**
 * TC-07 — Créer une classe avec des informations incomplètes
 * Prérequis : TC-01 et TC-09 → connexion gérée par BaseTest
 *
 * Scénarios :
 *  1. Level vide + Name renseigné → message d'erreur
 *  2. Level renseigné + Name vide → message d'erreur
 */
public class TC07_CreerClasseInfosIncompletes extends BaseTest {

    @Test
    public void TC07_creerClasseInfosIncompletes() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        ClassPage classPage         = new ClassPage(driver);

        test = extent.createTest("TC07 - Créer une classe avec des informations incomplètes");

        test.info("Cliquer sur le menu Classes");
        dashboardPage.cliquerSurClasses();

        test.info("Cliquer sur New Classes");
        classPage.cliquerSurNewClass();

        // Scénario 1 : Level vide, Name renseigné
        test.info("Laisser Levels vide, renseigner Name : Classes A");
        classPage.entrerNom("Classes A");

        test.info("Cliquer sur Create sans sélectionner un niveau");
        classPage.cliquerSurCreate();

        test.info("Vérifier le message d'erreur (Level vide)");
        Assert.assertTrue(classPage.isMessageErreurAffiche(),
            "Un message d'erreur doit s'afficher quand le champ Levels est vide");

        // Scénario 2 : Level renseigné, Name vide
        test.info("Sélectionner Niveau 1, laisser Name vide");
        classPage.selectionnerNiveau("Niveau 1");

        test.info("Cliquer sur Create sans saisir le nom");
        classPage.cliquerSurCreate();

        test.info("Vérifier le message d'erreur (Name vide)");
        Assert.assertTrue(classPage.isMessageErreurAffiche(),
            "Un message d'erreur doit s'afficher quand le champ Name est vide");
    }
}

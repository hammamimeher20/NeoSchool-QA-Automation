package tests;
 
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LevelPage;
 
/**
 * TC-06 — Créer un niveau
 * Prérequis : TC-01 et TC-09 → connexion gérée par BaseTest
 *
 * Scénarios :
 *  1. Champ Name vide → message d'erreur attendu
 *  2. Champ Name = "Niveau 1" → création réussie
 */
public class TC06_CreerNiveau extends BaseTest {
 
    @Test
    public void TC06_creerNiveau() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        LevelPage levelPage         = new LevelPage(driver);
 
        test = extent.createTest("TC06 - Créer un niveau");
 
        test.info("Aller sur Levels");
        dashboardPage.cliquerSurLevels();
 
        test.info("Cliquer sur New Levels");
        levelPage.cliquerSurNewLevel();
 
        // Scénario 1 : champ vide → erreur
        test.info("Laisser le champ Name vide et cliquer Create");
        levelPage.cliquerSurCreate();
 
        test.info("Vérifier le message d'erreur");
        Assert.assertTrue(levelPage.isMessageErreurAffiche(),
            "Un message d'erreur doit s'afficher si le nom est vide");
 
        // Scénario 2 : nom valide → succès
        test.info("Saisir le nom : Niveau 1");
        levelPage.entrerNom("Niveau 1");
 
        test.info("Cliquer sur Create");
        levelPage.cliquerSurCreate();
 
        test.info("Vérifier le message Created");
        Assert.assertTrue(levelPage.isMessageCreatedAffiche(),
            "Le message 'Created' doit s'afficher");
    }
}
package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * Classe de base pour tous les tests.
 *
 * Responsabilités :
 *  - @BeforeSuite  : initialiser le rapport ExtentReports
 *  - @BeforeMethod : ouvrir le browser + SE CONNECTER automatiquement
 *  - @AfterMethod  : screenshot si échec + fermer le browser
 *  - @AfterSuite   : sauvegarder le rapport
 *
 * Chaque test hérite de cette classe et commence DIRECTEMENT sur le Dashboard.
 */
public abstract class BaseTest {

    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    protected static final String BASE_URL    = "https://app.neoschool.innoteam.tn/neoschool";
    protected static final String SCHOOL_CODE = "neoschool";
    protected static final String EMAIL       = "admin@neoschool.test";
    protected static final String MOT_DE_PASSE = "edupassword!";

    private static final String REPORT_DIR =
        System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "custom-report";

    @BeforeSuite
    public void initialiserRapport() {
        new File(REPORT_DIR).mkdirs();
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
            REPORT_DIR + File.separator + "Execution Results - " + timestamp + ".html"
        );
        htmlReporter.config().setDocumentTitle("NeoSchool Automation Report");
        htmlReporter.config().setReportName("Tests Fonctionnels NeoSchool");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void ouvrirBrowserEtSeConnecter() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Naviguer vers l'application
        driver.get(BASE_URL);
        System.out.println(">>> URL : " + driver.getCurrentUrl());
        System.out.println(">>> TITRE : " + driver.getTitle());

        // Se connecter — prérequis commun à tous les tests
        LoginPage loginPage = new LoginPage(driver);
        loginPage.seConnecter(SCHOOL_CODE, EMAIL, MOT_DE_PASSE);
    }

    @AfterMethod
    public void fermerBrowser(ITestResult result) throws IOException {
        if (test != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail("ÉCHOUÉ : " + result.getName());
                test.fail(result.getThrowable());
                String path = prendreScreenshot(result.getName());
                test.addScreenCaptureFromPath(path);
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.skip("IGNORÉ : " + result.getName());
            } else {
                test.pass("RÉUSSI : " + result.getName());
            }
        }
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void sauvegarderRapport() {
        if (extent != null) extent.flush();
    }

    private String prendreScreenshot(String nomTest) throws IOException {
        String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destination = REPORT_DIR + File.separator + nomTest + "_" + date + ".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }
}

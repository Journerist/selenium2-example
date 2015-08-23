package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;


public class AureliaSkeletonNavigationTest {
    private WebDriver driver;

    WebDriverWait wait;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");

        DesiredCapabilities capabilitiesChrome = DesiredCapabilities.chrome();
        capabilitiesChrome.setBrowserName("chrome");

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),capabilities);

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testSimple() throws Exception {
        driver.get("http://localhost:9000");

        waitUntilLoadingDivDisappears();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Welcome | Aurelia");
    }

    private void waitUntilLoadingDivDisappears() {
        wait.until(
                ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("splash"))));
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}

package selenium;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import selenium.SeleniumTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ParallelSeleniumTest extends SeleniumTest {

    @BeforeClass
    @Parameters({"browser"})
    public void setupBrowserAndTimeouts(String browser) throws Exception {
        DesiredCapabilities capabilities;

        if (browser.equals("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        } else {
            capabilities = DesiredCapabilities.chrome();
        }

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"), capabilities);

        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

}

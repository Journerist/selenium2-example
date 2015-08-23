package e2e;

import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class AureliaSkeletonNavigationTest {
    private WebDriver driver;

    WebDriverWait wait;

    @BeforeClass
    @Parameters ({"browser"})
    public void setUp(String browser) throws Exception {
        DesiredCapabilities capabilities;

        if (browser.equals("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        } else {
            capabilities = DesiredCapabilities.chrome();
        }

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),capabilities);

        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void waitUntilAureliaIsCompletelyLoaded_nativeWait() throws Exception {
        driver.get("http://localhost:9000");
        waitUntilLoadingDivDisappears();

        String title = driver.getTitle();
        Assert.assertEquals(title, "Welcome | Aurelia");
    }

    @Test(dependsOnMethods="waitUntilAureliaIsCompletelyLoaded_nativeWait")
    public void waitUntilAnotherPageIsCompletelyLoaded_javaScriptWait() throws Exception {
        clickLinkByHref("#/child-router");
        wait.until( new Predicate<WebDriver>() {
                        public boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor)driver).executeScript("return document.getElementsByClassName('au-enter').length + \"\";").equals("0");
                        }
                    }
        );
        Assert.assertEquals(((JavascriptExecutor)driver).executeScript("return document.getElementsByClassName('au-enter').length"), 0L);
    }

    private void clickLinkByHref(String href) {
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        Iterator<WebElement> i = anchors.iterator();

        while(i.hasNext()) {
            WebElement anchor = i.next();
            if(anchor.getAttribute("href").contains(href)) {
                anchor.click();
                break;
            }
        }
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

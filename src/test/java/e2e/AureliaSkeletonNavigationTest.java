package e2e;

import selenium.ParallelSeleniumTest;
import selenium.utils.SeleniumAureliaAccessor;
import org.testng.Assert;
import org.testng.annotations.*;


public class AureliaSkeletonNavigationTest extends ParallelSeleniumTest {

    private SeleniumAureliaAccessor aurelia;

    @BeforeClass
    public void setup() throws Exception {
        aurelia = new SeleniumAureliaAccessor(driver, wait);
    }

    @Test(priority = 0)
    public void waitUntilAureliaIsCompletelyLoaded_nativeWait() throws Exception {
        driver.get("http://localhost:9000");
        aurelia.waitUntilAureliaLoaded();
        aurelia.waitUntilLoadingDivDisappears();

        Assert.assertEquals(driver.getTitle(), "Welcome | Aurelia");
    }

    @Test(priority = 1)
    public void waitUntilAnotherPageIsCompletelyLoaded_javaScriptWait() throws Exception {
        clickLinkByHref("#/child-router");
        waitUntilScriptResult("return document.getElementsByClassName('au-enter').length + '';", "0");
        Assert.assertEquals(getScriptResult("return document.getElementsByClassName('au-enter').length"), 0L);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}

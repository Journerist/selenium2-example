package selenium;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SeleniumTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected void clickLinkByHref(String href) {
        List<WebElement> anchors = driver.findElements(By.tagName("a"));

        for (WebElement anchor : anchors) {
            if (anchor.getAttribute("href").contains(href)) {
                anchor.click();
                break;
            }
        }
    }

    protected void waitUntilScriptResult(final String script, final String result) {
        wait.until(new Predicate<WebDriver>() {
                       public boolean apply(WebDriver driver) {
                           return ((JavascriptExecutor) driver).executeScript(script).equals(result);
                       }
                   }
        );
    }

    protected Object getScriptResult(String script) {
        return ((JavascriptExecutor) driver).executeScript(script);
    }
}
